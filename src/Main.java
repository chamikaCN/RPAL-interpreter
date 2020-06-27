import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main {

    static ArrayList<String> inputLines;
    static ArrayList<String[]> childArray;
    static ArrayList<_Node> tree, STtree;
    static HashMap<Integer, _Node> nodeCurrent;

    public static void main(String[] args) {
        inputLines = ReadWriteHandler.getInstance().readFile("F:\\Engineering\\Modules\\Semester 5\\Programming Languages\\Assignments\\cat.txt");
        childArray = new ArrayList<>();
        nodeCurrent = new HashMap<>();
        tree = new ArrayList<>();
        STtree = new ArrayList<>();

        nodeCurrent.put(-1, new RootNode());
        countDots();
        createTree();
        printTree(tree);
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
            }
            if (nodeCurrent.containsKey(Integer.parseInt(x[1]))) {
                nodeCurrent.replace(Integer.parseInt(x[1]), child);
            } else {
                nodeCurrent.put(Integer.parseInt(x[1]), child);
            }
            tree.add(child);
            setChildRelations(parent, child);
        }
    }

    private static _Node createNode(String x, _Node parent, int lev) {
        _Node returnNode;
        if (Objects.equals(x, "let")) {
            returnNode = new LetNode(parent, lev);
        } else if (Objects.equals(x, "function_form")) {
            returnNode = new FunctionFormNode(parent, lev);
        } else if (Objects.equals(x, "where")) {
            returnNode = new WhereNode(parent, lev);
        } else if (Objects.equals(x, "gamma")) {
            returnNode = new GammaNode(parent, lev);
        } else if (Objects.equals(x, "tau")) {
            returnNode = new TauNode(parent, lev);
        } else if (Objects.equals(x, "rec")) {
            returnNode = new RecNode(parent, lev);
        } else if (Objects.equals(x, ",")) {
            returnNode = new CommaNode(parent, lev);
        } else if (Objects.equals(x, "->")) {
            returnNode = new ArrowNode(parent, lev);
        } else if (Objects.equals(x, "eq")) {
            returnNode = new bool_EqNode(parent, lev);
        } else if (Objects.equals(x, "+")) {
            returnNode = new ari_PlusNode(parent, lev);
        } else if (Objects.equals(x, "-")) {
            returnNode = new ari_MinusNode(parent, lev);
        } else {
            returnNode = new FunctionFormNode(parent, lev);
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
                    System.out.println(n.type + " has parent " + n.getParent().type);
                }
                for (_Node m: ((_TrunkNode)n).getChildren()) {
                    System.out.println(n.type + " has child " + m.type);
                }
            }
        }
    }

}

