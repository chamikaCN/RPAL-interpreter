public class ari_AtNode extends _MultipolarNode implements Standardizable {
    public ari_AtNode(_Node parent) {
        super(parent);
        type = "@";
    }

    public _Node getExpression1() {
        return children.get(0);
    }

    public _Node getN() {
        return children.get(1);
    }

    public _Node getExpression2() {
        return children.get(2);
    }

    @Override
    public void standardize() {
        _Node E1 = getExpression1();
        _Node E2 = getExpression2();
        _Node N = getN();

        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);

        disconnect();

        GammaNode gamma = new GammaNode(par);
        par.removeAddChild(gamma, thisIndex);

        GammaNode secondgamma = new GammaNode(gamma);
        gamma.addChild(secondgamma, 0);

        gamma.addChild(E2, 1);
        E2.setParent(gamma);

        secondgamma.addChild(N, 0);
        N.setParent(secondgamma);

        secondgamma.addChild(E1, 1);
        E1.setParent(secondgamma);


        Main.addSTtree(gamma);
        Main.addSTtree(secondgamma);
        standardized = true;
    }
}
