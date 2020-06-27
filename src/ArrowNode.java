public class ArrowNode extends _MultipolarNode {

    public ArrowNode(_Node parent, int level) {
        super(parent,level);
        type = "->";
    }

    public _Node getElseClause() {
        return children.get(2);
    }

    public _Node getIfStatement() {
        return children.get(0);
    }

    public _Node getThenClause() {
        return children.get(1);
    }


}
