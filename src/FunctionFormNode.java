import java.util.ArrayList;

public class FunctionFormNode extends _MultipolarNode implements Standardizable {
    public FunctionFormNode(_Node parent, int level) {
        super(parent, level);
        type = "function_form";
    }

    public _Node getIdentifier() {
        return children.get(0);
    }

    public int getVariableCount() {
        return children.size() - 2;
    }

    public ArrayList<_Node> getVariables() {
        return new ArrayList<>(children.subList(1, children.size() - 1));
    }

    public _Node getExpression() {
        return children.get(children.size() - 1);
    }

    @Override
    public void standardize() {
        System.out.println("Standardized Function node");
        _Node P = getIdentifier();
        ArrayList<_Node> Vs = getVariables();
        int varCount = getVariableCount();
        _Node E = getExpression();
        int level = getLevel();
        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);

        disconnect();

        EqualNode eq = new EqualNode(par, level);
        eq.addChild(P, 0);
        P.setParent(eq);
        P.setLevel(level + 1);
        eq.addChild(standerizeLambda(varCount, eq, level, new ArrayList<>(Vs.subList(0, Vs.size())), E), 1);

        par.removeAddChild(eq, thisIndex);
        Main.addSTtree(eq);
        standardized = true;
    }

    private _Node standerizeLambda(int count, _Node parent, int lev, ArrayList<_Node> vars, _Node E) {
        LambdaNode lambda = new LambdaNode(parent, lev + 1);
        Main.addSTtree(lambda);
        lambda.addChild(vars.get(0), 0);
        vars.get(0).setParent(lambda);
        vars.get(0).setLevel(lev + 2);
        if (count < 2) {
            lambda.addChild(E, 1);
            E.setParent(lambda);
            System.out.println(E.type);
            E.setLevel(lev + 2);
            return lambda;
        } else {
            _Node k = standerizeLambda(count - 1, lambda, lev + 1, new ArrayList<>(vars.subList(1, vars.size())), E);
            lambda.addChild(k, 1);
            k.setParent(lambda);
            k.setLevel(lev + 2);
            return lambda;
        }
    }
}
