/**
 * Created by chamikanandasiri on 6/27/2020.
 */
public abstract class _UnaryOpNode extends _UnipolarNode implements Standardizable {
    public _UnaryOpNode(_Node parent) {
        super(parent);
    }

    @Override
    public void standardize() {
        System.out.println("Standardized Unary operator node");
        _Node E = getChild();

        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);
        int level = getLevel();

        disconnect();

        GammaNode gamma = new GammaNode(par,level);
        par.removeAddChild(gamma,thisIndex);

        LEAF_OPnode op = new LEAF_OPnode(gamma,level+1,type);
        gamma.addChild(op,0);

        gamma.addChild(E,1);
        E.setParent(gamma);


        Main.addSTtree(gamma);
        Main.addSTtree(op);

        standardized = true;

    }
}
