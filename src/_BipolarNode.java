public abstract class _BipolarNode extends _TrunkNode {
    public _BipolarNode(_Node parent) {
        super(parent);
    }

    public _Node getLeftChild(){
        return children.get(0);
    }

    public _Node getRightChild(){
        return children.get(1);
    }
}
