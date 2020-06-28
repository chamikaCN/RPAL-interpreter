import java.util.HashMap;

/**
 * Created by chamikanandasiri on 6/28/2020.
 */
public class Environment {
    private Delta linkedDelta;
    private HashMap<String,_Node> values;
    public Environment(Delta delta){
        linkedDelta = delta;
        values = new HashMap<>();
    }

    public void addValue(String s, _Node n){
        values.put(s,n);
    }

    public _Node getValue(String s){
        return values.get(s);
    }
}
