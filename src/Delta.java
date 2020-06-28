import java.util.Stack;

/**
 * Created by chamikanandasiri on 6/28/2020.
 */
public class Delta extends _Node {
    private Environment linkedEnv;
    private Stack<_Node> valuestack;
    private _Node id;

    public Delta(_Node parent) {
        super(parent);
        valuestack = new Stack<>();
    }

    public void pushValue(_Node n){
        valuestack.push(n);
    }

    public _Node popValue(){
        return valuestack.pop();
    }

    public _Node peekValue(){
        return valuestack.peek();
    }

    public _Node getId() {
        return id;
    }

    public void setId(_Node id) {
        this.id = id;
    }
}
