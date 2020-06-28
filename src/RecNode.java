import java.util.Objects;

public class RecNode extends _UnipolarNode {
    public RecNode(_Node parent,int level) {
        super(parent,level);
        type = "rec";
    }

//    @Override
//    public void standardize() {
//        if (Objects.equals(getChild().type, "=")) {
//            System.out.println("Standardized Rec node");
//            _Node X = ((_BipolarNode) getChild()).getLeftChild();
//            _Node E = ((_BipolarNode) getChild()).getRightChild();
//
//            _TrunkNode par = (_TrunkNode) getParent();
//            int thisIndex = par.getChildren().indexOf(this);
//            int level = getLevel();
//
//            getChild().disconnect();
//            disconnect();
//
//            EqualNode eq = new EqualNode(par,level);
//            par.removeAddChild(eq, thisIndex);
//
//            eq.addChild(X,0);
//
//
//            GammaNode gamma = new GammaNode(par, level);
//
//            LambdaNode lambda = new LambdaNode(gamma, level + 1);
//
//            gamma.addChild(lambda, 0);
//            lambda.addChild(X, 0);
//            lambda.addChild(P, 1);
//            X.setParent(lambda);
//            X.setLevel(level + 2);
//            P.setParent(lambda);
//            P.setLevel(level + 2);
//            gamma.addChild(E, 1);
//            E.setParent(gamma);
//            E.setLevel(level + 1);
//            Main.addSTtree(gamma);
//            Main.addSTtree(lambda);
//            standardized = true;
//
//        } else {
//            Main.addSTtree(this);
//        }
//    }
}
