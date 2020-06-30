public class Eta extends  _Node {
    private Delta linkedDelta;
    public Eta(_Node parent, Delta del) {
        super(parent);
        linkedDelta = del;
        type = "Eta";
    }

    public Delta getLinkedDelta() {
        return linkedDelta;
    }
}
