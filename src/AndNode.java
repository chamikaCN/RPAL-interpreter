import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class AndNode extends _MultipolarNode {
    public AndNode(_Node parent, int level) {
        super(parent, level);
        type = "and";
    }

    public ArrayList<_Node> getElements() {
        return children;
    }

    public int getElementCount() {
        return children.size();
    }
}
