/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class _UnipolarNode extends _TrunkNode {
    public _UnipolarNode(_Node parent, int level) {
        super(parent, level);
    }

    public _Node getChild() {
        return children.get(0);
    }
}
