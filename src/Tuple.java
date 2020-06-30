import java.util.ArrayList;

/**
 * Created by chamikanandasiri on 6/30/2020.
 */
public class Tuple extends _Node {
    ArrayList<_Node> tupleValues;
    public Tuple(_Node parent) {
        super(parent);
        type = "tuple";
        tupleValues = new ArrayList<>();
    }

    public _Node getValueAt(int m){
        return tupleValues.get(m-1);
    }

    public void addValue(_Node n){
        tupleValues.add(n);
    }

    public int getCount(){
        return tupleValues.size();
    }
}
