/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _UnipolarNode extends _TrunkNode {
    public _UnipolarNode(_Node parent) {
        super(parent);
    }

    public _Node getChild() {
        return children.get(0);
    }
}
