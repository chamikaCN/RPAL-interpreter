/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class bool_GreatEqualNode extends _BinaryOpNode {
    public bool_GreatEqualNode(_Node parent, int level) {
        super(parent, level);
        type = "ge";
    }
}