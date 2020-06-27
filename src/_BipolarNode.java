/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class _BipolarNode extends _TrunkNode {
    public _BipolarNode(_Node parent, int level) {
        super(parent, level);
    }

    public _Node getLeftChild(){
        return children.get(0);
    }

    public _Node getRightChild(){
        return children.get(1);
    }
}
