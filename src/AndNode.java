import java.util.ArrayList;

public class AndNode extends _MultipolarNode implements Standardizable {
    public AndNode(_Node parent) {
        super(parent);
        type = "and";
    }

    public ArrayList<_Node> getElements() {
        return children;
    }

    public int getElementCount() {
        return children.size();
    }

    @Override
    public void standardize() {

        boolean equality = true;
        ArrayList<_Node> Eqs = getElements();
        for (_Node eq : Eqs) {
            if (eq.type != "=") {
                equality = false;
                break;
            }
        }

        if (equality) {
            _TrunkNode par = (_TrunkNode) getParent();
            int thisIndex = par.getChildren().indexOf(this);

            disconnect();
            for (_Node eq : Eqs) {
                eq.disconnect();
            }

            EqualNode equal = new EqualNode(par);
            par.removeAddChild(equal, thisIndex);

            CommaNode comma = new CommaNode(equal);
            equal.addChild(comma, 0);

            TauNode tau = new TauNode(equal);
            equal.addChild(tau, 1);

            for (_Node eq : Eqs) {
                _Node X = ((_BipolarNode) eq).getLeftChild();
                _Node E = ((_BipolarNode) eq).getRightChild();
                comma.addChild(X);
                comma.addChild(E);
                X.setParent(comma);
                E.setParent(comma);
            }

            Main.addSTtree(equal);
            Main.addSTtree(comma);
            Main.addSTtree(tau);
            standardized = true;
        }
        else {
            Main.addSTtree(this);
        }
    }
}
