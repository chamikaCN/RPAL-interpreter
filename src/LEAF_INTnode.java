/**
 * Created by chamikanandasiri on 6/23/2020.
 */
public class LEAF_INTnode extends _LeafNode {
    private int val;
    public LEAF_INTnode(_Node parent, int value, int level) {
        super(parent,level,String.valueOf(value));
        this.val = val;
        type = "<INT:>";
    }

    public int getInt() {
        return val;
    }

}
