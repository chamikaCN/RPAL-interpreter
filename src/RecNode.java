import java.util.Objects;

public class RecNode extends _UnipolarNode implements Standardizable {
    public RecNode(_Node parent) {
        super(parent);
        type = "rec";
    }

    @Override
    public void standardize() {
        if (Objects.equals(getChild().type, "=")) {
            System.out.println("Standardized Rec node");
            _Node X = ((_BipolarNode) getChild()).getLeftChild();
            _Node X2 = new LEAF_IDnode(X.getParent(), ((LEAF_IDnode) X).getId());

            _Node E = ((_BipolarNode) getChild()).getRightChild();

            _TrunkNode par = (_TrunkNode) getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            getChild().disconnect();
            disconnect();

            EqualNode eq = new EqualNode(par);
            par.removeAddChild(eq, thisIndex);

            eq.addChild(X2, 0);
            X2.setParent(eq);

            GammaNode gamma = new GammaNode(eq);
            eq.addChild(gamma, 1);

            LEAF_YStarNode y = new LEAF_YStarNode(gamma);
            gamma.addChild(y, 0);
            LambdaNode lambda = new LambdaNode(gamma);
            gamma.addChild(lambda, 1);

            lambda.addChild(X, 0);
            X.setParent(lambda);
            lambda.addChild(E, 1);
            E.setParent(lambda);

            Main.addSTtree(eq);
            Main.addSTtree(X2);
            Main.addSTtree(gamma);
            Main.addSTtree(y);
            Main.addSTtree(lambda);
            standardized = true;

        } else {
            Main.addSTtree(this);
        }
    }
}
