public abstract class _ValueNode extends _LeafNode {
    private String value;
    public _ValueNode(_Node parent, String value) {
        super(parent);
        this.value = value;
    }

    public String getLeafValue(){
        return value;
    }
}

