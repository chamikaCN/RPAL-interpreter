/**
 * Created by chamikanandasiri on 6/23/2020.
 */
public class LEAF_INTnode extends _ValueNode {
    private int val;
    public LEAF_INTnode(_Node parent, int value) {
        super(parent,String.valueOf(value));
        this.val = value;
        type = "<INT:>";
    }

    public int getInt() {
        return val;
    }

}
