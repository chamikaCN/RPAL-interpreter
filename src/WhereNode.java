import java.util.Objects;

public class WhereNode extends _BipolarNode implements Standardizable {
    public WhereNode(_Node parent, int level) {
        super(parent, level);
        type = "where";
    }

    @Override
    public void standardize() {

        if (Objects.equals(getRightChild().type, "=")) {
            System.out.println("Standardized Where node");
            _Node X = ((_BipolarNode) getRightChild()).getLeftChild();
            _Node E = ((_BipolarNode) getRightChild()).getRightChild();
            _Node P = getLeftChild();
            _TrunkNode par = (_TrunkNode) getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            getRightChild().disconnect();
            disconnect();

            GammaNode gamma = new GammaNode(par, level);
            LambdaNode lambda = new LambdaNode(gamma, level + 1);
            par.removeAddChild(gamma, thisIndex);
            gamma.addChild(lambda, 0);
            lambda.addChild(X, 0);
            lambda.addChild(P, 1);
            X.setParent(lambda);
            X.setLevel(level + 2);
            P.setParent(lambda);
            P.setLevel(level + 2);
            gamma.addChild(E, 1);
            E.setParent(gamma);
            E.setLevel(level + 1);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        } else {
            Main.addSTtree(this);
        }
    }

}
