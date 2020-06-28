import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class RootNode extends _TrunkNode{
    ArrayList<_Node> children;
    public RootNode() {
        super(null, -1);
        type = "Root";
    }
}
