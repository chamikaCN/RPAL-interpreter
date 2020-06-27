/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class bool_NotNode extends _UnaryOpNode {
    public bool_NotNode(_Node parent, int level) {
        super(parent, level);
        type = "not";
    }
}
