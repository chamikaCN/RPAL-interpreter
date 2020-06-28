import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _TrunkNode extends _Node {
    ArrayList<_Node> children;
    boolean standardized = false;

    public _TrunkNode(_Node parent) {
        super(parent);
        children = new ArrayList<>();
    }

    public void addChild(_Node child, int index) {
        children.add(index, child);
    }

    public void removeAddChild(_Node child, int index) {
        children.remove(index);
        if (children.size() <= index){
            children.add(child);
        }else {
            children.add(index, child);
        }
    }

    public void addChild(_Node child) {
        children.add(child);
    }

    public boolean getStandardized() {
        return standardized;
    }

    public ArrayList<_Node> getChildren() {
        return children;
    }
}
