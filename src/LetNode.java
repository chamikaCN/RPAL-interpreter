import java.util.Objects;

public class LetNode extends _BipolarNode {
    public LetNode(_Node parent, int level) {
        super(parent,level);
        type = "let";
    }


//    public void Standersize() {
//        if(Objects.equals(getLeftChild().type, "=")){
//            System.out.println("Standardized Let node");
//            _Node X = ((_BipolarNode)getLeftChild()).getLeftChild();
//            _Node E = ((_BipolarNode)getLeftChild()).getRightChild();
//            _Node P = getRightChild();
//            _Node par = getParent();
//
//            GammaNode gamma = new GammaNode(par,level);
//            LambdaNode lambda = new LambdaNode(gamma,level+1);
//            par.addChild(gamma,0);
//            gamma.addChild(lambda,0);
//            lambda.addChild(X,0);
//            lambda.addChild(P,1);
//            X.setParent(lambda);
//            P.setParent(lambda);
//            gamma.addChild(E,1);
//            E.setParent(gamma);
//            TestMain.addnewSTNode(gamma);
//            TestMain.addnewSTNode(lambda);
//            standardized = true;
//        }
//    }

}
