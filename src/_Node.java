public abstract class _Node {
    String type;
    private _Node parent;
    boolean disconnected = false;


    public _Node(_Node parent) {
        this.parent = parent;
    }

    public _Node getParent() {
        return parent;
    }

    public void setParent(_Node par) {
        this.parent = par;
    }

    public int getLevel() {
        return getParent().getLevel() + 1;
    }

    public void disconnect(){
        disconnected = true;
    }
}
