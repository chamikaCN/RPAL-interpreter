import java.util.Objects;

public class LetNode extends _BipolarNode implements Standardizable {
    public LetNode(_Node parent) {
        super(parent);
        type = "let";
    }

    @Override
    public void standardize() {

        if (Objects.equals(getLeftChild().type, "=")) {
            _Node X = ((_BipolarNode)getLeftChild()).getLeftChild();
            _Node E = ((_BipolarNode)getLeftChild()).getRightChild();
            _Node P = getRightChild();
            _TrunkNode par = (_TrunkNode)getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            disconnect();
            getLeftChild().disconnect();

            GammaNode gamma = new GammaNode(par);
            LambdaNode lambda = new LambdaNode(gamma);
            par.removeAddChild(gamma,thisIndex);
            gamma.addChild(lambda,0);
            lambda.addChild(X,0);
            lambda.addChild(P,1);
            X.setParent(lambda);
            P.setParent(lambda);
            gamma.addChild(E,1);
            E.setParent(gamma);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        }else {
            Main.addSTtree(this);
        }
    }
}
