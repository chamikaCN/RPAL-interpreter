public class LEAF_STRnode extends _ValueNode {
    private int val;
    public LEAF_STRnode(_Node parent, String value) {
        super(parent,value);
        this.val = val;
        type = "<STR:>";
    }

    public int getString() {
        return val;
    }

}
