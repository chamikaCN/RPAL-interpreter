/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class _ValueNode extends _LeafNode {
    private String value;
    public _ValueNode(_Node parent, int level, String value) {
        super(parent, level);
        this.value = value;
    }

    public String getLeafValue(){
        return value;
    }
}

