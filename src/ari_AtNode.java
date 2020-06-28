/**
 * Created by chamikanandasiri on 6/27/2020.
 */
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
        System.out.println("Standardized At node");
        _Node E1 = getExpression1();
        _Node E2 = getExpression2();
        _Node N = getN();

        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);
        int level = getLevel();

        disconnect();

        GammaNode gamma = new GammaNode(par, level);
        par.removeAddChild(gamma, thisIndex);
        gamma.addChild(E2, 1);
        E2.setParent(gamma);

        GammaNode secondgamma = new GammaNode(gamma, level + 1);
        gamma.addChild(secondgamma, 0);
        secondgamma.addChild(E1, 1);
        E1.setParent(secondgamma);
        secondgamma.addChild(N, 0);
        N.setParent(secondgamma);

        Main.addSTtree(gamma);
        Main.addSTtree(secondgamma);
        standardized = true;
    }
}
