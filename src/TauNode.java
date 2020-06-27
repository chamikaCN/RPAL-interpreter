import java.util.ArrayList;

public class TauNode extends _MultipolarNode {


    public TauNode(_Node parent, int level) {
        super(parent,level);
        type = "tau";
    }

//    public void Standersize() {
//
//        System.out.println("Standardized ID Tau node");
//
//        ArrayList<_Node> Es = getElements();
//        int eleCount = getElementCount();
//
//        _Node par = getParent();
//        int thisIndex = par.getChildren().indexOf(this);
//
//        _Node gam = standerizeTau(eleCount, par, level, new ArrayList<_Node>(Es.subList(0, Es.size())));
//
//        par.addChild(gam, thisIndex);
//        standardized = true;
//    }
//
//    private _Node standerizeTau(int count, _Node parent, int lev, ArrayList<_Node> elements) {
//        GammaNode mainGamma = new GammaNode(parent, lev);
//        TestMain.addnewSTNode(mainGamma);
//        GammaNode secondaryGamma = new GammaNode(mainGamma, lev + 1);
//        TestMain.addnewSTNode(secondaryGamma);
//        mainGamma.addChild(secondaryGamma, 0);
//        mainGamma.addChild(elements.get(0), 1);
//        elements.get(0).setParent(mainGamma);
//        AugNode aug = new AugNode(secondaryGamma, lev + 2);
//        secondaryGamma.addChild(aug, 0);
//        TestMain.addnewSTNode(aug);
//
//        if (count < 2) {
//            LEAF_NilNode nil = new LEAF_NilNode(secondaryGamma, lev + 2);
//            secondaryGamma.addChild(nil, 1);
//            TestMain.addnewSTNode(nil);
//            System.out.println("came");
//            return mainGamma;
//        } else {
//            _Node k = standerizeTau(count - 1, secondaryGamma, lev + 2, new ArrayList<_Node>(elements.subList(1, elements.size())));
//            secondaryGamma.addChild(k, 1);
//            return mainGamma;
//        }
//    }

    public ArrayList<_Node> getElements() {
        return children;
    }

    public int getElementCount() {
        return children.size();
    }

}
