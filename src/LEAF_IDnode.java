public class LEAF_IDnode extends _ValueNode {
    private String Id;

    public LEAF_IDnode(_Node parent, String id, int level) {
        super(parent,level,id);
        this.Id = id;
        type = "<ID:>";

    }

    public String getId() {
        return Id;
    }
}
