import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _TrunkNode extends _Node {
    ArrayList<_Node> children;
    public _TrunkNode(_Node parent, int level) {
        super(parent, level);
        children = new ArrayList<>();
    }

    public void addChild(_Node child, int index){
        children.add(index,child);
    }

    public void addChild(_Node child){
        children.add(child);
    }

    public ArrayList<_Node> getChildren(){
        return children;
    }
}
