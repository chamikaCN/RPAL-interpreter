public class LEAF_OPnode extends _ValueNode {
    private String operater;
    public LEAF_OPnode(_Node parent,String op) {
        super(parent,op);
        type = "<OP:>";
        operater = op;
    }

    public String getOperater() {
        return operater;
    }
}
