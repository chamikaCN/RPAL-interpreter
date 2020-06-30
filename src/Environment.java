import java.util.HashMap;

public class Environment extends _Node {
    private HashMap<String,_Node> values;
    private int id;
    public Environment(Environment environment,int id){
        super(environment);
        values = new HashMap<>();
        type = "envi";
        this.id =id;
    }

    public void addValue(String s, _Node n){
        values.put(s,n);
    }

    public _Node getValue(String s){
        return values.get(s);
    }

    public _Node getdelta() {
        return super.getParent();
    }

    public boolean hasValue(String s){return values.containsKey(s);}

    public int getValueCount(){return values.size();}

    public int getId() {
        return id;
    }
}
