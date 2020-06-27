import java.util.ArrayList;

public class FunctionFormNode extends _MultipolarNode {
    public FunctionFormNode(_Node parent,int level) {
        super(parent,level);
        type = "function_form";

    }


//    public void Standersize() {
//        System.out.println("Standardized function node");
//        _Node P = getIdentifier();
//        ArrayList<_Node> Vs = getVariables();
//        int varCount = getVariableCount();
//
//        _Node E = getExpression();
//
//        _Node par = getParent();
//        int thisIndex = par.getChildren().indexOf(this);
//        int treeIndex = TestMain.tree.indexOf(this);
//
//
//        EqualNode eq = new EqualNode(par,level);
//        eq.addChild(P,0);
//        eq.addChild(standerizeLambda(varCount,eq,level,new ArrayList<_Node>(Vs.subList(0,Vs.size())),E),1);
//
//        par.addChild(eq,thisIndex);
//        TestMain.addnewSTNode(eq);
//        standardized = true;
//
//    }
//
//    private _Node standerizeLambda(int count, _Node parent, int lev, ArrayList<_Node> vars, _Node E) {
//        LambdaNode lambda = new LambdaNode(parent,lev+1);
//        TestMain.addnewSTNode(lambda);
//        lambda.addChild(vars.get(0),0);
//        vars.get(0).setParent(lambda);
//        if(count < 2){
//            lambda.addChild(E,1);
//            E.setParent(lambda);
//            return lambda;
//        }else{
//            _Node k = standerizeLambda(count-1,lambda,lev+1,new ArrayList<_Node>(vars.subList(1,vars.size())),E);
//            lambda.addChild(k,0);
//            k.setParent(lambda);
//            return lambda;
//        }
//    }

    public _Node getIdentifier() {
        return children.get(0);
    }

    public int getVariableCount() {
        return children.size() - 2;
    }

    public ArrayList<_Node> getVariables() {
        return new ArrayList<_Node>(children.subList(1, children.size() - 1));
    }

    public _Node getExpression() {
        return children.get(1);
    }

}
