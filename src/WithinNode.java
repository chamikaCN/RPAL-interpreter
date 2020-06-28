import java.util.Objects;

/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public class WithinNode extends _BipolarNode implements Standardizable {
    public WithinNode(_Node parent) {
        super(parent);
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

            EqualNode eq = new EqualNode(par);
            par.removeAddChild(eq, thisIndex);
            eq.addChild(X2, 0);
            X2.setParent(eq);

            GammaNode gamma = new GammaNode(eq);
            eq.addChild(gamma, 1);
            gamma.addChild(E1, 1);
            E1.setParent(gamma);

            LambdaNode lambda = new LambdaNode(gamma);
            gamma.addChild(lambda, 0);
            lambda.addChild(X1, 0);
            X1.setParent(lambda);
            lambda.addChild(E2, 1);
            E2.setParent(lambda);

            Main.addSTtree(eq);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        } else {
            Main.addSTtree(this);
        }
    }
}
