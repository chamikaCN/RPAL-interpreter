import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class RootNode extends _Node {
    ArrayList<_Node> children;
    public RootNode() {
        super(null, -1);
        type = "Root";
    }

    public void addChild(_Node child, int index){
        children.add(index,child);
    }

    public ArrayList<_Node> getChildren(){
        return children;
    }
}
