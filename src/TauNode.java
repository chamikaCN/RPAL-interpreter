import java.util.ArrayList;

public class TauNode extends _MultipolarNode implements Standardizable {


    public TauNode(_Node parent, int level) {
        super(parent, level);
        type = "tau";

    }

    public ArrayList<_Node> getElements() {
        return children;
    }

    public int getElementCount() {
        return children.size();
    }

    @Override
    public void standardize() {

        System.out.println("Standardized ID Tau node");

        ArrayList<_Node> Es = getElements();
        int eleCount = getElementCount();

        _TrunkNode par = (_TrunkNode) getParent();
        int thisIndex = par.getChildren().indexOf(this);
        int level = getLevel();

        disconnect();

        _Node gam = standerizeTau(eleCount, par, level, new ArrayList<>(Es.subList(0, Es.size())));

        par.removeAddChild(gam, thisIndex);
        standardized = true;
    }

    private _Node standerizeTau(int count, _Node parent, int lev, ArrayList<_Node> elements) {
        GammaNode mainGamma = new GammaNode(parent, lev);
        Main.addSTtree(mainGamma);
        GammaNode secondaryGamma = new GammaNode(mainGamma, lev + 1);
        Main.addSTtree(secondaryGamma);
        mainGamma.addChild(secondaryGamma, 0);
        mainGamma.addChild(elements.get(0), 1);
        elements.get(0).setParent(mainGamma);
        elements.get(0).setLevel(lev+1);
        AugNode aug = new AugNode(secondaryGamma, lev + 2);
        secondaryGamma.addChild(aug, 0);
        Main.addSTtree(aug);

        if (count < 2) {
            LEAF_NilNode nil = new LEAF_NilNode(secondaryGamma, lev + 2);
            secondaryGamma.addChild(nil, 1);
            Main.addSTtree(nil);
            System.out.println("came");
            return mainGamma;
        } else {
            _Node k = standerizeTau(count - 1, secondaryGamma, lev + 2, new ArrayList<>(elements.subList(1, elements.size())));
            secondaryGamma.addChild(k, 1);
            return mainGamma;
        }
    }
}
