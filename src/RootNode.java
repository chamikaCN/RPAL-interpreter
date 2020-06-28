import java.util.ArrayList;

public class RootNode extends _TrunkNode{
    ArrayList<_Node> children;
    public RootNode() {
        super(null);
        type = "Root";
    }

    @Override
    public int getLevel() {
        return -1;
    }
}
