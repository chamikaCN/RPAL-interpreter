/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class LEAF_OPnode extends _ValueNode {
    private String operater;
    public LEAF_OPnode(_Node parent, int level,String op) {
        super(parent, level,op);
        type = "<OP:>";
        operater = op;
    }

    public String getOperater() {
        return operater;
    }
}
