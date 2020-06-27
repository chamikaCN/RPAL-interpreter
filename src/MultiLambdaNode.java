import java.util.ArrayList;

public class MultiLambdaNode extends _MultipolarNode {
    public MultiLambdaNode(_Node parent, int level) {
        super(parent,level);
        type = "multilambda";
    }


//    public void Standersize() {
//        _Node E = getExpression();
//        ArrayList<_Node> Vs = getVariables();
//        int varCount = getVariableCount();
//
//        _Node par = getParent();
//        int thisIndex = par.getChildren().indexOf(this);
//
//        _Node lambda = standerizeMultiLambda(varCount, par, level, new ArrayList<_Node>(Vs.subList(0, Vs.size())),E);
//
//        par.addChild(lambda, thisIndex);
//        standardized = true;
//    }
//
//    private _Node standerizeMultiLambda(int count, _Node parent, int lev, ArrayList<_Node> vars, _Node E) {
//        LambdaNode lambda = new LambdaNode(parent, lev);
//        TestMain.addnewSTNode(lambda);
//        lambda.addChild(vars.get(0), 0);
//
//        if (count < 2) {
//            lambda.addChild(E,1);
//            E.setParent(lambda);
//            return lambda;
//        } else {
//            _Node k = standerizeMultiLambda(count - 1, lambda, lev + 1, new ArrayList<_Node>(vars.subList(1, vars.size())),E);
//            lambda.addChild(k, 1);
//            return lambda;
//        }
//    }



    public _Node getExpression(){
        return children.get(children.size()-1);
    }

    public int getVariableCount() {
        return children.size() - 2;
    }

    public ArrayList<_Node> getVariables() {
        return new ArrayList<_Node>(children.subList(1, children.size() - 1));
    }
}
