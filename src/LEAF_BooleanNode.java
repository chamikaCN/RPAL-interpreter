public class LEAF_BooleanNode extends  _ValueNode {
    boolean val;
    public LEAF_BooleanNode(_Node parent,boolean b) {
        super(parent,String.valueOf(b));
        type = "boolean";
        val = b;
    }

    public boolean getBooleanValue() {
        return val;
    }
}