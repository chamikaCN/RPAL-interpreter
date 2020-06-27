/**
 * Created by chamikanandasiri on 6/23/2020.
 */
public class LEAF_STRnode extends _ValueNode {
    private int val;
    public LEAF_STRnode(_Node parent, String value, int level) {
        super(parent,level,value);
        this.val = val;
        type = "<STR:>";
    }

    public int getString() {
        return val;
    }

}
