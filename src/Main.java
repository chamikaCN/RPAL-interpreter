import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    static ArrayList<String> inputLines;
    static ArrayList<String[]> childArray;
    static ArrayList<_Node> AST, STtree;
    static HashMap<Integer, _Node> nodeCurrent;

    public static void main(String[] args) {
        //takes input from the given file
        inputLines = ReadWriteHandler.getInstance().readFile(args[0]);
        childArray = new ArrayList<>();
        nodeCurrent = new HashMap<>();
        AST = new ArrayList<>();
        STtree = new ArrayList<>();

        nodeCurrent.put(-1, new RootNode());
        countDots();
        createTree();
        LoopingStandardization(AST);
        CSEmachine.getInstance().Evalutate(STtree);
    }

    //create node name, level pairs
    private static void countDots() {
        for (String s : inputLines) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '.') {
                    break;
                } else {
                    count += 1;
                    s = s.substring(1);
                    i -= 1;
                }
            }
            childArray.add(new String[]{s, String.valueOf(count)});
        }
    }

    //create new nodes for all node names in the child array
    private static void createTree() {
        for (String[] x : childArray) {
            _Node parent = nodeCurrent.get(Integer.parseInt(x[1]) - 1);
            _Node child = null;
            if (x[0].charAt(0) != '<') {
                child = createNode(x[0], parent);
            } else if (x[0].charAt(2) == 'D') {
                child = new LEAF_IDnode(parent, x[0].substring(4, x[0].length() - 1));
            } else if (x[0].charAt(2) == 'N') {
                child = new LEAF_INTnode(parent, Integer.parseInt(x[0].substring(5, x[0].length() - 1)));
            } else if (x[0].charAt(2) == 'T') {
                child = new LEAF_STRnode(parent, x[0].substring(6, x[0].length() - 2));
            } else if (x[0].charAt(1) == 'n') {
                child = new LEAF_NilNode(parent);
            }
            if (nodeCurrent.containsKey(Integer.parseInt(x[1]))) {
                nodeCurrent.replace(Integer.parseInt(x[1]), child);
            } else {
                nodeCurrent.put(Integer.parseInt(x[1]), child);
            }
            AST.add(child);
            setChildRelations(parent, child);
        }
    }

    //create all trunk nodes
    private static _Node createNode(String x, _Node parent) {
        _Node returnNode;
        if (Objects.equals(x, "let")) {
            returnNode = new LetNode(parent);
        } else if (Objects.equals(x, "lambda")) {
            returnNode = new LambdaNode(parent);
        } else if (Objects.equals(x, "where")) {
            returnNode = new WhereNode(parent);

        } else if (Objects.equals(x, "tau")) {
            returnNode = new TauNode(parent);
        } else if (Objects.equals(x, "aug")) {
            returnNode = new AugNode(parent);
        } else if (Objects.equals(x, "->")) {
            returnNode = new ArrowNode(parent);

        } else if (Objects.equals(x, "or")) {
            returnNode = new bool_OrNode(parent);
        } else if (Objects.equals(x, "&")) {
            returnNode = new bool_AndNode(parent);
        } else if (Objects.equals(x, "not")) {
            returnNode = new bool_NotNode(parent);
        } else if (Objects.equals(x, "gr")) {
            returnNode = new bool_GreaterNode(parent);
        } else if (Objects.equals(x, "ge")) {
            returnNode = new bool_GreatEqualNode(parent);
        } else if (Objects.equals(x, "ls")) {
            returnNode = new bool_LesserNode(parent);
        } else if (Objects.equals(x, "le")) {
            returnNode = new bool_LessEqualNode(parent);
        } else if (Objects.equals(x, "eq")) {
            returnNode = new bool_EqualNode(parent);
        } else if (Objects.equals(x, "ne")) {
            returnNode = new bool_NotEqualNode(parent);

        } else if (Objects.equals(x, "+")) {
            returnNode = new ari_PlusNode(parent);
        } else if (Objects.equals(x, "-")) {
            returnNode = new ari_MinusNode(parent);
        } else if (Objects.equals(x, "neg")) {
            returnNode = new ari_NegativeNode(parent);
        } else if (Objects.equals(x, "*")) {
            returnNode = new ari_StarNode(parent);
        } else if (Objects.equals(x, "/")) {
            returnNode = new ari_SlashNode(parent);
        } else if (Objects.equals(x, "**")) {
            returnNode = new ari_DoubleStarNode(parent);
        } else if (Objects.equals(x, "@")) {
            returnNode = new ari_AtNode(parent);

        } else if (Objects.equals(x, "gamma")) {
            returnNode = new GammaNode(parent);
        } else if (Objects.equals(x, "true")) {
            returnNode = new LEAF_BooleanNode(parent, true);
        } else if (Objects.equals(x, "false")) {
            returnNode = new LEAF_BooleanNode(parent, false);
        } else if (Objects.equals(x, "nil")) {
            returnNode = new LEAF_NilNode(parent);
        } else if (Objects.equals(x, "dummy")) {
            returnNode = new LEAF_DummyNode(parent);

        } else if (Objects.equals(x, "within")) {
            returnNode = new WithinNode(parent);
        } else if (Objects.equals(x, "and")) {
            returnNode = new AndNode(parent);
        } else if (Objects.equals(x, "rec")) {
            returnNode = new RecNode(parent);
        } else if (Objects.equals(x, "=")) {
            returnNode = new EqualNode(parent);
        } else if (Objects.equals(x, "function_form")) {
            returnNode = new FunctionFormNode(parent);

        } else if (Objects.equals(x, "()")) {
            returnNode = new LEAF_ParenthesisNode(parent);
        } else if (Objects.equals(x, ",")) {
            returnNode = new CommaNode(parent);

        } else {
            returnNode = new LEAF_DummyNode(parent);
        }
        return returnNode;
    }

    //add relevent children to each nodes children arraylist
    private static void setChildRelations(_Node parent, _Node child) {
        if (parent != null && child != null && (parent instanceof _TrunkNode)) {
            ((_TrunkNode) parent).addChild(child);

        }
    }

    public static void addSTtree(_Node node) {
        STtree.add(node);
    }

    //loop through standardization process until necessary nodes are standerized
    private static void LoopingStandardization(ArrayList<_Node> currentTree) {
        boolean t = true;
        ArrayList<_Node> TempTree = currentTree;
        while (t) {
            STtree = new ArrayList<>();
            t = Standardization(TempTree);
            TempTree = new ArrayList<>(STtree);
        }

    }

    //call each node's standardization method
    private static boolean Standardization(ArrayList<_Node> tree) {
        boolean didAnyStandardized = false;
        for (_Node n : tree) {
            if ((n instanceof Standardizable) && (!((_TrunkNode) n).getStandardized())) {
                ((Standardizable) n).standardize();
                didAnyStandardized = didAnyStandardized || ((_TrunkNode) n).getStandardized();
            } else if (!(n instanceof Standardizable) && !(n.disconnected)) {
                addSTtree(n);
            }
        }
        return didAnyStandardized;
    }

}

