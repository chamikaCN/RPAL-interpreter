public class AugNode extends _BinaryOpNode {
    public AugNode(_Node parent, int level) {
        super(parent, level);
        type = "aug";
    }

}
