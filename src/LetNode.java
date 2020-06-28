import java.util.Objects;

public class LetNode extends _BipolarNode implements Standardizable {
    public LetNode(_Node parent, int level) {
        super(parent, level);
        type = "let";
    }

    @Override
    public void standardize() {

        if (Objects.equals(getLeftChild().type, "=")) {
            System.out.println("Standardized Let node");
            _Node X = ((_BipolarNode)getLeftChild()).getLeftChild();
            _Node E = ((_BipolarNode)getLeftChild()).getRightChild();
            _Node P = getRightChild();
            _TrunkNode par = (_TrunkNode)getParent();
            int thisIndex = par.getChildren().indexOf(this);
            int level = getLevel();

            disconnect();
            getLeftChild().disconnect();

            GammaNode gamma = new GammaNode(par,level);
            LambdaNode lambda = new LambdaNode(gamma,level+1);
            par.removeAddChild(gamma,thisIndex);
            gamma.addChild(lambda,0);
            lambda.addChild(X,0);
            lambda.addChild(P,1);
            X.setParent(lambda);
            X.setLevel(level+2);
            P.setParent(lambda);
            P.setLevel(level+2);
            gamma.addChild(E,1);
            E.setParent(gamma);
            E.setLevel(level+1);
            Main.addSTtree(gamma);
            Main.addSTtree(lambda);
            standardized = true;

        }else {
            Main.addSTtree(this);
        }
    }
}
