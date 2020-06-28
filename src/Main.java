import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    static ArrayList<String> inputLines;
    static ArrayList<String[]> childArray;
    static ArrayList<_Node> AST, STtree;
    static HashMap<Integer, _Node> nodeCurrent;

    public static void main(String[] args) {
        inputLines = ReadWriteHandler.getInstance().readFile("F:\\Engineering\\Modules\\Semester 5\\Programming Languages\\RPAL PRO\\inputs\\fn1.txt");
        childArray = new ArrayList<>();
        nodeCurrent = new HashMap<>();
        AST = new ArrayList<>();
        STtree = new ArrayList<>();

        nodeCurrent.put(-1, new RootNode());
        countDots();
        createTree();
        printTree(AST);
        System.out.println("###############################");
        LoopingStandard(AST);
        printTree(STtree);
    }

    private static void LoopingStandard(ArrayList<_Node> currentTree){
        boolean t = true;
        ArrayList<_Node> TempTree = currentTree;
        while (t){
            STtree = new ArrayList<>();
            t = Standardization(TempTree);
            System.out.println("###############################");
            printTree(STtree);
            System.out.println("###############################");
            TempTree = new ArrayList<>(STtree);
        }

    }

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

    private static void createTree() {
        for (String[] x : childArray) {
            _Node parent = nodeCurrent.get(Integer.parseInt(x[1]) - 1);
            _Node child = null;
            if (x[0].charAt(0) != '<') {
                child = createNode(x[0], parent, Integer.parseInt(x[1]));
            } else if (x[0].charAt(2) == 'D') {
                child = new LEAF_IDnode(parent, x[0].substring(4, x[0].length() - 1), Integer.parseInt(x[1]));
            } else if (x[0].charAt(2) == 'N') {
                child = new LEAF_INTnode(parent, Integer.parseInt(x[0].substring(5, x[0].length() - 1)), Integer.parseInt(x[1]));
            } else if (x[0].charAt(2) == 'T') {
                child = new LEAF_STRnode(parent, x[0].substring(6, x[0].length() - 2), Integer.parseInt(x[1]));
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

    private static _Node createNode(String x, _Node parent, int lev) {
        _Node returnNode;
        if (Objects.equals(x, "let")) {
            returnNode = new LetNode(parent, lev);
        } else if (Objects.equals(x, "lambda")) {
            returnNode = new LambdaNode(parent, lev);
        } else if (Objects.equals(x, "where")) {
            returnNode = new WhereNode(parent, lev);

        } else if (Objects.equals(x, "tau")) {
            returnNode = new TauNode(parent, lev);
        } else if (Objects.equals(x, "aug")) {
            returnNode = new AugNode(parent, lev);
        } else if (Objects.equals(x, "->")) {
            returnNode = new ArrowNode(parent, lev);

        } else if (Objects.equals(x, "or")) {
            returnNode = new bool_OrNode(parent, lev);
        } else if (Objects.equals(x, "&")) {
            returnNode = new bool_AndNode(parent, lev);
        } else if (Objects.equals(x, "not")) {
            returnNode = new bool_NotNode(parent, lev);
        } else if (Objects.equals(x, "gr")) {
            returnNode = new bool_GreaterNode(parent, lev);
        } else if (Objects.equals(x, "ge")) {
            returnNode = new bool_GreatEqualNode(parent, lev);
        } else if (Objects.equals(x, "ls")) {
            returnNode = new bool_LesserNode(parent, lev);
        } else if (Objects.equals(x, "le")) {
            returnNode = new bool_LessEqualNode(parent, lev);
        } else if (Objects.equals(x, "eq")) {
            returnNode = new bool_EqualNode(parent, lev);
        } else if (Objects.equals(x, "ne")) {
            returnNode = new bool_NotEqualNode(parent, lev);

        } else if (Objects.equals(x, "+")) {
            returnNode = new ari_PlusNode(parent, lev);
        } else if (Objects.equals(x, "-")) {
            returnNode = new ari_MinusNode(parent, lev);
        } else if (Objects.equals(x, "neg")) {
            returnNode = new ari_NegativeNode(parent, lev);
        } else if (Objects.equals(x, "*")) {
            returnNode = new ari_StarNode(parent, lev);
        } else if (Objects.equals(x, "/")) {
            returnNode = new ari_SlashNode(parent, lev);
        } else if (Objects.equals(x, "**")) {
            returnNode = new ari_DoubleStarNode(parent, lev);
        } else if (Objects.equals(x, "@")) {
            returnNode = new ari_AtNode(parent, lev);

        } else if (Objects.equals(x, "gamma")) {
            returnNode = new GammaNode(parent, lev);
        } else if (Objects.equals(x, "true")) {
            returnNode = new LEAF_TrueNode(parent, lev);
        } else if (Objects.equals(x, "false")) {
            returnNode = new LEAF_FalseNode(parent, lev);
        } else if (Objects.equals(x, "nil")) {
            returnNode = new LEAF_NilNode(parent, lev);
        } else if (Objects.equals(x, "dummy")) {
            returnNode = new LEAF_DummyNode(parent, lev);

        } else if (Objects.equals(x, "within")) {
            returnNode = new WithinNode(parent, lev);
        } else if (Objects.equals(x, "and")) {
            returnNode = new AndNode(parent, lev);
        } else if (Objects.equals(x, "rec")) {
            returnNode = new RecNode(parent, lev);
        } else if (Objects.equals(x, "=")) {
            returnNode = new EqualNode(parent, lev);
        }else if (Objects.equals(x, "function_form")) {
            returnNode = new FunctionFormNode(parent, lev);

        } else if (Objects.equals(x, "()")) {
            returnNode = new LEAF_ParenthesisNode(parent, lev);
        } else if (Objects.equals(x, ",")) {
            returnNode = new CommaNode(parent, lev);

        } else {
            returnNode = new LEAF_DummyNode(parent, lev);
        }
        return returnNode;
    }

    private static void setChildRelations(_Node parent, _Node child) {
        if (parent != null && child != null && (parent instanceof _TrunkNode)) {
            ((_TrunkNode)parent).addChild(child);

        }
    }

    private static void printTree(ArrayList<_Node> nodeTree) {
        for (_Node n : nodeTree) {
            if ((n instanceof _TrunkNode) && ((_TrunkNode)n).getChildren().size()>0) {
                if( n.getParent() != null) {
                    System.out.println(n.type + " at level " + n.getLevel() + " has parent " + n.getParent().type);
                }
                for (_Node m: ((_TrunkNode)n).getChildren()) {
                    System.out.println(n.type + " at level " + n.getLevel() +" has child " + m.type);
                }
            }
        }
    }

    public static void addSTtree(_Node node){
        STtree.add(node);
    }

    private static boolean Standardization(ArrayList<_Node> tree) {
        boolean didAnyStandardized = false;
        for (_Node n: tree) {
            if((n instanceof Standardizable)&&(!((_TrunkNode)n).getStandardized())){
                ((Standardizable) n).standardize();
                didAnyStandardized = didAnyStandardized || ((_TrunkNode)n).getStandardized();
                System.out.println("Main standard "+ n.type + " at " + n.getLevel());
            }else if(!(n instanceof Standardizable) && !(n.disconnected)){
                addSTtree(n);
                System.out.println("Added without "+ n.type + " at " + n.getLevel());
            }
        }
    return didAnyStandardized;
    }

}

