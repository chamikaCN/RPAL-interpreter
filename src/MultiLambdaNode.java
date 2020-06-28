import java.util.ArrayList;

public class MultiLambdaNode extends _MultipolarNode implements Standardizable {
    public MultiLambdaNode(_Node parent, int level) {
        super(parent,level);
        type = "multilambda";
    }


    public _Node getExpression(){
        return children.get(children.size()-1);
    }

    public int getVariableCount() {
        return children.size() - 2;
    }

    public ArrayList<_Node> getVariables() {
        return new ArrayList<>(children.subList(1, children.size() - 1));
    }

    @Override
    public void standardize() {

        System.out.println("Standardized Multi Lambda node");
        _Node E = getExpression();
        ArrayList<_Node> Vs = getVariables();
        int varCount = getVariableCount();

        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);
        int level = getLevel();

        disconnect();

        _Node lambda = standerizeMultiLambda(varCount, par, level, new ArrayList<>(Vs.subList(0, Vs.size())),E);

        par.removeAddChild(lambda, thisIndex);
        standardized = true;
    }

    private _Node standerizeMultiLambda(int count, _Node parent, int lev, ArrayList<_Node> vars, _Node E) {
        LambdaNode lambda = new LambdaNode(parent, lev);
        Main.addSTtree(lambda);
        lambda.addChild(vars.get(0), 0);

        if (count < 2) {
            lambda.addChild(E,1);
            E.setParent(lambda);
            E.setLevel(lev+1);
            return lambda;
        } else {
            _Node k = standerizeMultiLambda(count - 1, lambda, lev + 1, new ArrayList<>(vars.subList(1, vars.size())),E);
            lambda.addChild(k, 1);
            return lambda;
        }
    }
}
