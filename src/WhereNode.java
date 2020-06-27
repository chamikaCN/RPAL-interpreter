import java.util.Objects;

public class WhereNode extends _BipolarNode {
    public WhereNode(_Node parent,int level) {
        super(parent,level);
        type = "where";
    }

//    public void Standersize() {
//        if(Objects.equals(getRightChild().type, "=")){
//            System.out.println("Standardized where node");
//
//            _Node X = ((_BipolarNode)getRightChild()).getLeftChild();
//            _Node E = ((_BipolarNode)getRightChild()).getRightChild();
//            _Node P = getLeftChild();
//
//            LambdaNode lambda = new LambdaNode(this,level+1);
//            addChild(lambda,0);
//            lambda.addChild(X,0);
//            lambda.addChild(P,1);
//            X.setParent(lambda);
//            P.setParent(lambda);
//            E.setParent(this);
//            addChild(E,1);
//            TestMain.addnewSTNode(lambda);
//
//            standardized = true;
//        }
//    }

}
