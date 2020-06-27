/**
 * Created by chamikanandasiri on 6/27/2020.
 */

import java.util.ArrayList;

public abstract class _Node {
    String type;
    private int level;
    private _Node parent;


    public _Node(_Node parent, int level) {
        this.parent = parent;
        this.level = level;
    }

    public _Node getParent() {
        return parent;
    }

    public void setParent(_Node par) {
        this.parent = par;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
