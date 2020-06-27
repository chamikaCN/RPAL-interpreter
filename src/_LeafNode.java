/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _LeafNode extends _Node {
    private String value;
    public _LeafNode(_Node parent, int level, String value) {
        super(parent, level);
        this.value = value;
    }

    public String getLeafValue(){
        return value;
    }
}
