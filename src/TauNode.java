import java.util.ArrayList;

public class TauNode extends _MultipolarNode{

    public TauNode(_Node parent) {
        super(parent);
        type = "tau";
    }

    public ArrayList<_Node> getElements() {
        return children;
    }

    public int getElementCount() {
        return children.size();
    }

}
