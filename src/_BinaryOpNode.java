/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _BinaryOpNode extends _BipolarNode {
    public _BinaryOpNode(_Node parent) {
        super(parent);
    }

//    @Override
//    public void standardize() {
//        System.out.println("Standardized Binary operator node "+ type );
//        _Node E1 = getLeftChild();
//        _Node E2 = getRightChild();
//
//        _TrunkNode par = (_TrunkNode) getParent();
//        int thisIndex = par.getChildren().indexOf(this);
//        int level = getLevel();
//
//        disconnect();
//
//        GammaNode gamma = new GammaNode(par);
//        par.removeAddChild(gamma,thisIndex);
//
//        GammaNode secondgamma = new GammaNode(gamma);
//        gamma.addChild(secondgamma,0);
//
//        gamma.addChild(E2,1);
//        E2.setParent(gamma);
//
//        LEAF_OPnode op = new LEAF_OPnode(secondgamma,type);
//        secondgamma.addChild(op,0);
//
//        secondgamma.addChild(E1,1);
//        E1.setParent(secondgamma);
//
//
//
//        Main.addSTtree(gamma);
//        Main.addSTtree(secondgamma);
//        Main.addSTtree(op);
//
//        standardized = true;
//    }
}
