import java.util.Objects;

public class WhereNode extends _BipolarNode implements Standardizable {
    public WhereNode(_Node parent) {
        super(parent);
        type = "where";
    }

    @Override
    public void standardize() {

        if (Objects.equals(getRightChild().type, "=")) {
            _Node X = ((_BipolarNode) getRightChild()).getLeftChild();
            _Node E = ((_BipolarNode) getRightChild()).getRightChild();
            _Node P = getLeftChild();
            _TrunkNode par = (_TrunkNode) getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            getRightChild().disconnect();
            disconnect();

            GammaNode gamma = new GammaNode(par);
            LambdaNode lambda = new LambdaNode(gamma);
            par.removeAddChild(gamma, thisIndex);
            gamma.addChild(lambda, 0);
            lambda.addChild(X, 0);
            lambda.addChild(P, 1);
            X.setParent(lambda);
            P.setParent(lambda);
            gamma.addChild(E, 1);
            E.setParent(gamma);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        } else {
            Main.addSTtree(this);
        }
    }

}
