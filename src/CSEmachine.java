import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class CSEmachine {

    private Stack<_Node> valueStack;
    private Stack<_Node> controlStack;
    private ArrayList<_Node> StructuredTree;
    private ArrayList<Delta> deltas;
    private Delta rootDelta;
    private Delta currentDelta;
    private Environment primitiveEnvironment;
    private Environment currentEnvironment;
    private int envCount = 0;


    public CSEmachine(ArrayList<_Node> ST) {
        StructuredTree = ST;
        controlStack = new Stack<>();
        valueStack = new Stack<>();
        deltas = new ArrayList<>();
        primitiveEnvironment = new Environment(null, 0);
        envCount++;
        rootDelta = new Delta(null);
        rootDelta.setBindings(null);
        deltas.add(rootDelta);
        _Node root = null;
        for (_Node b : ST) {
            if (b.getParent() instanceof RootNode) {
                root = b;
                break;
            }
        }
        traverseTree(root, rootDelta);
        evaluateMachine();
    }

    public void traverseTree(_Node root, Delta delta) {

        if (Objects.equals(root.type, "lambda")) {
            delta.pushValue(startNewDelta(delta, ((LambdaNode) root).getRightChild(), ((LambdaNode) root).getLeftChild()));
            return;
        } else if (Objects.equals(root.type, "->")) {
            AddArrowNode(root, delta);
            return;
        } else {
            delta.pushValue(root);
        }
        if (root instanceof _LeafNode)
            return;

        for (_Node child : ((_TrunkNode) root).getChildren()) {
            traverseTree(child, delta);
        }
    }


    private void AddArrowNode(_Node arrow, Delta parentdelta) {
        Delta falseDelta = new Delta(arrow);
        traverseTree(((ArrowNode) arrow).getElseClause(), falseDelta);
        parentdelta.pushValue(falseDelta);
        Delta trueDelta = new Delta(arrow);
        traverseTree(((ArrowNode) arrow).getThenClause(), trueDelta);
        parentdelta.pushValue(trueDelta);
        parentdelta.pushValue(new Beta(arrow));
        traverseTree(((ArrowNode) arrow).getIfStatement(), parentdelta);
    }

    private Delta startNewDelta(Delta parent, _Node newRoot, _Node id) {
        Delta curDelta = new Delta(parent);
        if (id instanceof CommaNode) {
            CommaNode com = (CommaNode) id;
            int varCount = com.getChildren().size();
            for (int f = 0; f < varCount; f++) {
                curDelta.setBindings(com.getChildren().get(f));
            }
        } else {
            curDelta.setBindings(id);
        }
        deltas.add(curDelta);
        traverseTree(newRoot, curDelta);
        return curDelta;
    }


    private void AddDeltaToControlStack(Delta delta) {
        for (int f = 0; f < delta.getValuestack().size(); f++) {
            controlStack.push(delta.getValuestack().elementAt(f));
        }
    }


    private void evaluateMachine() {
        currentDelta = rootDelta;
        currentEnvironment = primitiveEnvironment;
        controlStack.push(currentEnvironment);
        valueStack.push(currentEnvironment);
        AddDeltaToControlStack(rootDelta);
        while (controlStack.size() > 0) {
            evaluateRound();
        }
    }

    private void evaluateRound() {
        _Node m = controlStack.pop();
        if (m instanceof _BinaryOpNode) {
            doBinaryOperation(m);
        } else if (m instanceof _UnaryOpNode) {
            doUnaryOperation(m);
        } else if (m instanceof LEAF_IDnode) {
            handleIdentifier(m);
        } else if (m instanceof GammaNode) {
            applyGamma(m);
        } else if (m instanceof Beta) {
            applyArrowFunction(m);
        } else if (m instanceof TauNode) {
            createTuple(m);
        } else if (m instanceof Environment) {
            removeEnvironment(m);
        } else if (m instanceof Delta) {
            ((Delta) m).setLinkedEnv(currentEnvironment);
            valueStack.push(m);
        } else {
            valueStack.push(m);
        }
    }

    private void createTuple(_Node m) {
        TauNode t = (TauNode) m;
        int count = t.getElementCount();
        Tuple tup = new Tuple(t);
        for (int v = 0; v < count; v++) {
            _Node n = valueStack.pop();
            tup.addValue(n);
        }
        valueStack.push(tup);
    }

    private void applyArrowFunction(_Node beta) {
        _Node truthVal = valueStack.pop();
        if (truthVal instanceof LEAF_BooleanNode) {
            boolean val = ((LEAF_BooleanNode) truthVal).getBooleanValue();
            Delta trueDel = (Delta) controlStack.pop();
            Delta falseDel = (Delta) controlStack.pop();
            if (val) {
                AddDeltaToControlStack(trueDel);
            } else {
                AddDeltaToControlStack(falseDel);
            }
        } else {
            System.out.println("Error in Arrow Function operation");
        }
    }

    private void removeEnvironment(_Node m) {
        _Node valueBeforeEnv = valueStack.pop();
        _Node stackEnv = valueStack.pop();
        if (m == stackEnv) {
            valueStack.push(valueBeforeEnv);
            if (currentDelta != rootDelta) {
                currentDelta = (Delta) currentDelta.getParent();
            }
            if (currentEnvironment != primitiveEnvironment) {
                currentEnvironment = findPreviousEnvironment();
            }
        } else {
            System.out.println("Error in Remove Env operation");
        }
    }

    private Environment findPreviousEnvironment() {
        Environment pre = null;
        for (int h = valueStack.size() - 1; h >= 0; h--) {
            _Node m = valueStack.elementAt(h);
            if ((m instanceof Environment) && m != currentEnvironment) {
                pre = (Environment) m;
                break;
            }
        }
        return pre;
    }

    private void handleIdentifier(_Node identifier) {
        _Node rand = valueStack.pop();
        _Node gamma = controlStack.pop();
        if (!isKnownIdentifier(identifier, rand)) {
            valueStack.push(rand);
            controlStack.push(gamma);
            lookUpEnvironment(identifier);
        }
    }


    private void lookUpEnvironment(_Node m) {
        LEAF_IDnode iDnode = (LEAF_IDnode) m;
        _Node value = goThroughEnvironments(iDnode.getLeafValue(), currentEnvironment);
        if (value != null) {
            valueStack.push(value);
        } else {
            System.out.println("Error: " + iDnode.getLeafValue() + " not found");
        }

    }

    private _Node goThroughEnvironments(String s, Environment env) {
        if (env.hasValue(s)) {
            return env.getValue(s);
        } else if (env.getParent() == null) {
            return null;
        } else {
            return goThroughEnvironments(s, (Environment) env.getParent());
        }
    }

    private void doUnaryOperation(_Node op) {
        _Node value = valueStack.pop();
        if (Objects.equals(op.type, "not")) {
            LEAF_BooleanNode bool = (LEAF_BooleanNode) value;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), !bool.getBooleanValue()));
        } else if (Objects.equals(op.type, "neg")) {
            LEAF_INTnode inti = (LEAF_INTnode) value;
            valueStack.push(new LEAF_INTnode(op.getParent(), (inti.getInt() * (-1))));
        } else {
            System.out.println("Error in unary operation");
        }
    }

    private void doBinaryOperation(_Node op) {
        _Node value1 = valueStack.pop();
        _Node value2 = valueStack.pop();
        if (Objects.equals(op.type, "+")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_INTnode(op.getParent(), val1.getInt() + val2.getInt()));
        } else if (Objects.equals(op.type, "-")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_INTnode(op.getParent(), val1.getInt() - val2.getInt()));
        } else if (Objects.equals(op.type, "*")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_INTnode(op.getParent(), val1.getInt() * val2.getInt()));
        } else if (Objects.equals(op.type, "/")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_INTnode(op.getParent(), val1.getInt() / val2.getInt()));
        } else if (Objects.equals(op.type, "**")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_INTnode(op.getParent(), val1.getInt() ^ val2.getInt()));
        } else if (Objects.equals(op.type, "ge")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() >= val2.getInt()));
        } else if (Objects.equals(op.type, "gr")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() > val2.getInt()));
        } else if (Objects.equals(op.type, "le")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() <= val2.getInt()));
        } else if (Objects.equals(op.type, "ls")) {
            LEAF_INTnode val1 = (LEAF_INTnode) value1;
            LEAF_INTnode val2 = (LEAF_INTnode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() < val2.getInt()));
        } else if (Objects.equals(op.type, "eq")) {
            if (value1 instanceof LEAF_INTnode && value2 instanceof LEAF_INTnode) {
                LEAF_INTnode val1 = (LEAF_INTnode) value1;
                LEAF_INTnode val2 = (LEAF_INTnode) value2;
                valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() == val2.getInt()));
            } else if (value1 instanceof LEAF_STRnode && value2 instanceof LEAF_STRnode) {
                LEAF_STRnode val1 = (LEAF_STRnode) value1;
                LEAF_STRnode val2 = (LEAF_STRnode) value2;
                valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getString() == val2.getString()));
            }
        } else if (Objects.equals(op.type, "ne")) {
            if (value1 instanceof LEAF_INTnode && value2 instanceof LEAF_INTnode) {
                LEAF_INTnode val1 = (LEAF_INTnode) value1;
                LEAF_INTnode val2 = (LEAF_INTnode) value2;
                valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getInt() != val2.getInt()));
            } else if (value1 instanceof LEAF_STRnode && value2 instanceof LEAF_STRnode) {
                LEAF_STRnode val1 = (LEAF_STRnode) value1;
                LEAF_STRnode val2 = (LEAF_STRnode) value2;
                valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getString() != val2.getString()));
            }
        } else if (Objects.equals(op.type, "&")) {
            LEAF_BooleanNode val1 = (LEAF_BooleanNode) value1;
            LEAF_BooleanNode val2 = (LEAF_BooleanNode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getBooleanValue() && val2.getBooleanValue()));
        } else if (Objects.equals(op.type, "or")) {
            LEAF_BooleanNode val1 = (LEAF_BooleanNode) value1;
            LEAF_BooleanNode val2 = (LEAF_BooleanNode) value2;
            valueStack.push(new LEAF_BooleanNode(op.getParent(), val1.getBooleanValue() || val2.getBooleanValue()));
        } else {
            System.out.println("Error in unary operation");
        }
    }

    private void applyGamma(_Node m) {
        _Node rator = valueStack.pop();
        if (rator instanceof Delta) {
            Delta del = (Delta) rator;
            currentDelta = del;
            currentEnvironment = new Environment(currentDelta.getLinkedEnv(), envCount);
            envCount++;
            int bindingcount = currentDelta.getBindingcount();

            if (bindingcount < 2) {
                _Node r = valueStack.pop();
                currentEnvironment.addValue(((_ValueNode) currentDelta.getBindingAt(0)).getLeafValue(), r);
            } else {
                Tuple tup = (Tuple) valueStack.pop();
                for (int h = 0; h < bindingcount; h++) {
                    currentEnvironment.addValue(((_ValueNode) currentDelta.getBindingAt(h)).getLeafValue(), tup.getValueAt(h + 1));
                }
            }
            valueStack.push(currentEnvironment);
            controlStack.push(currentEnvironment);
            AddDeltaToControlStack(currentDelta);
        } else if (rator instanceof LEAF_YStarNode) {
            Delta del = (Delta) valueStack.pop();
            valueStack.push(new Eta(rator, del));
        } else if (rator instanceof Tuple) {
            Tuple tup = (Tuple) rator;
            LEAF_INTnode index = (LEAF_INTnode) valueStack.pop();
            _Node retrieved = tup.getValueAt(index.getInt());
            valueStack.push(retrieved);
        } else if (rator instanceof Eta) {
            controlStack.push(m);
            controlStack.push(new GammaNode(m));
            Delta del = ((Eta) rator).getLinkedDelta();
            valueStack.push(rator);
            valueStack.push(del);
        } else {
            System.out.println("Error in gamma operation");
        }

    }

    private boolean isKnownIdentifier(_Node id, _Node rand) {
        LEAF_IDnode identifier = (LEAF_IDnode) id;
        String val = identifier.getLeafValue();
        if (Objects.equals(val, "Isstring")) {
            valueStack.push(new LEAF_BooleanNode(rand, rand instanceof LEAF_STRnode));
            return true;
        } else if (Objects.equals(val, "Isinteger")) {
            valueStack.push(new LEAF_BooleanNode(rand, rand instanceof LEAF_INTnode));
            return true;
        } else if (Objects.equals(val, "Isfunction")) {
            valueStack.push(new LEAF_BooleanNode(rand, rand instanceof Delta));
            return true;
        } else if (Objects.equals(val, "Istruthvalue")) {
            valueStack.push(new LEAF_BooleanNode(rand, rand instanceof LEAF_BooleanNode));
            return true;
        } else if ((Objects.equals(val, "print")) || (Objects.equals(val, "Print"))) {
            System.out.println(((_ValueNode) rand).getLeafValue());
            valueStack.push(new LEAF_DummyNode(rand));
            return true;
        } else if ((Objects.equals(val, "stem")) || (Objects.equals(val, "Stem"))) {
            valueStack.push(new LEAF_STRnode(rand, ((LEAF_STRnode) rand).getLeafValue().substring(0, 1)));
            return true;
        } else if ((Objects.equals(val, "stern")) || (Objects.equals(val, "Stern"))) {
            valueStack.push(new LEAF_STRnode(rand, ((LEAF_STRnode) rand).getLeafValue().substring(1)));
            return true;
        } else if ((Objects.equals(val, "conc")) || (Objects.equals(val, "Conc"))) {
            _Node firstStr = valueStack.pop();
            valueStack.push(new LEAF_STRnode(rand, ((LEAF_STRnode) firstStr).getLeafValue().concat(((LEAF_STRnode) rand).getLeafValue())));
            return true;
        } else if ((Objects.equals(val, "order")) || (Objects.equals(val, "Order"))) {
            valueStack.push(new LEAF_INTnode(rand, ((Tuple) rand).getCount()));
            return true;
        } else {
            return false;
        }

    }
}
