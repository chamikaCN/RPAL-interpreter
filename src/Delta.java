import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by chamikanandasiri on 6/28/2020.
 */
public class Delta extends _Node {
    private Environment linkedEnv;
    private Stack<_Node> valuestack;
    private _Node binding;

    public Delta(_Node parent) {
        super(parent);
        valuestack = new Stack<>();
        type = "delta";
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

    public _Node getBinding() {
        return binding;
    }

    public Stack<_Node> getValuestack() {
        return valuestack;
    }

    public void setBinding(_Node binding) {
        this.binding =binding;
    }

    public Environment getLinkedEnv() {
        return linkedEnv;
    }

    public void setLinkedEnv(Environment linkedEnv) {
        this.linkedEnv = linkedEnv;
    }
}
