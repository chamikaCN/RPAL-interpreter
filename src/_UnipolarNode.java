public abstract class _UnipolarNode extends _TrunkNode {
    public _UnipolarNode(_Node parent) {
        super(parent);
    }

    public _Node getChild() {
        return children.get(0);
    }
}
