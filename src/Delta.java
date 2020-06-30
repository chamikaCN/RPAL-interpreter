import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by chamikanandasiri on 6/28/2020.
 */
public class Delta extends _Node {
    private Environment linkedEnv;
    private Stack<_Node> valuestack;
    private _Node binding;
    private ArrayList<_Node> bindings;

    public Delta(_Node parent) {
        super(parent);
        valuestack = new Stack<>();
        type = "delta";
        bindings = new ArrayList<>();
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

    public _Node getBindingAt(int index){
        return bindings.get(index);
    }

    public void setBindings(_Node newBinding) {
        bindings.add(newBinding);
    }

    public int getBindingcount(){
        return bindings.size();
    }

    public Environment getLinkedEnv() {
        return linkedEnv;
    }

    public void setLinkedEnv(Environment linkedEnv) {
        this.linkedEnv = linkedEnv;
    }
}
