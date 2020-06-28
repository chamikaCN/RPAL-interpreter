public class ArrowNode extends _MultipolarNode {

    public ArrowNode(_Node parent) {
        super(parent);
        type = "->";
    }

    public _Node getElseClause() {
        return children.get(2);
    }

    public _Node getIfStatement() {
        return children.get(0);
    }

    public _Node getThenClause() {
        return children.get(1);
    }


//    @Override
//    public void standardize() {
//        System.out.println("Standardized Arrow node");
//
//        _Node B = getIfStatement();
//        _Node T = getThenClause();
//        _Node F = getElseClause();
//
//        int level = getLevel();
//        _TrunkNode par = (_TrunkNode) getParent();
//        int thisIndex = par.getChildren().indexOf(this);
//
//        disconnect();
//
//        GammaNode gamma1 = new GammaNode(par);
//        par.removeAddChild(gamma1, thisIndex);
//
//        GammaNode gamma2 = new GammaNode(gamma1);
//        gamma1.addChild(gamma2, 0);
//
//        LEAF_NilNode nil = new LEAF_NilNode(gamma1);
//        gamma1.addChild(nil, 1);
//
//        GammaNode gamma3 = new GammaNode(gamma2);
//        gamma2.addChild(gamma3, 0);
//
//        LambdaNode lambda1 = new LambdaNode(gamma2);
//        gamma2.addChild(lambda1, 1);
//
//        LEAF_ParenthesisNode paranthesisF = new LEAF_ParenthesisNode(lambda1);
//        lambda1.addChild(paranthesisF, 0);
//        lambda1.addChild(F, 1);
//        F.setParent(lambda1);
//
//        GammaNode gamma4 = new GammaNode(gamma3);
//        gamma3.addChild(gamma4, 0);
//
//        LambdaNode lambda2 = new LambdaNode(gamma3);
//        gamma3.addChild(lambda2, 1);
//
//        LEAF_IDnode cond = new LEAF_IDnode(gamma4, "Cond");
//        gamma4.addChild(cond, 0);
//        gamma4.addChild(B, 1);
//        B.setParent(gamma4);
//
//        LEAF_ParenthesisNode paranthesisT = new LEAF_ParenthesisNode(lambda2);
//        lambda2.addChild(paranthesisT, 0);
//        lambda2.addChild(T, 1);
//        T.setParent(lambda2);
//
//        Main.addSTtree(gamma1);
//        Main.addSTtree(gamma2);
//        Main.addSTtree(nil);
//        Main.addSTtree(gamma3);
//        Main.addSTtree(gamma4);
//        Main.addSTtree(lambda1);
//        Main.addSTtree(lambda2);
//        Main.addSTtree(paranthesisF);
//        Main.addSTtree(paranthesisT);
//        Main.addSTtree(cond);
//        standardized = true;
//
//    }

}
