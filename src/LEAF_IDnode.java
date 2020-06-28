public class LEAF_IDnode extends _ValueNode {
    private String Id;

    public LEAF_IDnode(_Node parent, String id) {
        super(parent,id);
        this.Id = id;
        type = "<ID:>";

    }

    public String getId() {
        return Id;
    }
}
