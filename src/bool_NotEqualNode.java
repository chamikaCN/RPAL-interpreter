/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class bool_NotEqualNode extends _BinaryOpNode {
    public bool_NotEqualNode(_Node parent, int level) {
        super(parent, level);
        type = "ne";
    }
}
