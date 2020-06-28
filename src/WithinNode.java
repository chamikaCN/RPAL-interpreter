import java.util.Objects;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class WithinNode extends _BipolarNode implements Standardizable {
    public WithinNode(_Node parent, int level) {
        super(parent, level);
        type = "within";
    }

    @Override
    public void standardize() {

        if (Objects.equals(getLeftChild().type, "=") && Objects.equals(getRightChild().type, "=")) {
            System.out.println("Standardized Multi Lambda node");
            _Node X1 = ((_BipolarNode) getLeftChild()).getLeftChild();
            _Node E1 = ((_BipolarNode) getLeftChild()).getRightChild();
            _Node X2 = ((_BipolarNode) getRightChild()).getLeftChild();
            _Node E2 = ((_BipolarNode) getRightChild()).getRightChild();

            _TrunkNode par = (_TrunkNode) getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            disconnect();
            getLeftChild().disconnect();
            getRightChild().disconnect();

            EqualNode eq = new EqualNode(par, level);
            par.removeAddChild(eq, thisIndex);
            eq.addChild(X2, 0);
            X2.setParent(eq);
            X2.setLevel(level + 1);

            GammaNode gamma = new GammaNode(eq, level + 1);
            eq.addChild(gamma, 1);
            gamma.addChild(E1, 1);
            E1.setParent(gamma);
            E1.setLevel(level + 2);

            LambdaNode lambda = new LambdaNode(gamma, level + 2);
            gamma.addChild(lambda, 0);
            lambda.addChild(X1, 0);
            X1.setParent(lambda);
            X1.setLevel(level + 3);
            lambda.addChild(E2, 1);
            E2.setParent(lambda);
            E2.setLevel(level + 3);

            Main.addSTtree(eq);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        } else {
            Main.addSTtree(this);
        }
    }
}
