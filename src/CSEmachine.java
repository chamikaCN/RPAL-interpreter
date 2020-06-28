import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by chamikanandasiri on 6/28/2020.
 */
public class CSEmachine {

    private Stack<_Node> valueStack;
    private ArrayList<_Node> StructuredTree;
    private ArrayList<Delta> deltas;
    private Delta rootDelta;


    public CSEmachine(ArrayList<_Node> ST){
        StructuredTree = ST;
        valueStack = new Stack<>();
        deltas = new ArrayList<>();
        rootDelta = new Delta(null);
        rootDelta.setId(null);
        deltas.add(rootDelta);
        _Node root = null;
        for (_Node b: ST) {
            if(b.getParent() instanceof RootNode){
                root = b;
                break;
            }
        }
        traverseTree(root,rootDelta);
        System.out.println(deltas.size());
    }

    public void traverseTree( _Node root,Delta delta){

        if(Objects.equals(root.type, "lambda")){
            System.out.println("************************");
            delta.pushValue(startNewDelta(delta,((LambdaNode)root).getRightChild(),((LambdaNode)root).getLeftChild()));
            System.out.println("==========================");
            return;
        }else {
            delta.pushValue(root);
            System.out.println(root.type);
        }
        if (root instanceof _LeafNode)
            return;

        for (_Node child: ((_TrunkNode)root).getChildren()) {
            traverseTree(child,delta);
        }
    }

    private Delta startNewDelta(Delta parent,_Node newRoot, _Node id) {
        Delta curDelta = new Delta(parent);
        curDelta.setId(id);
        deltas.add(curDelta);
        traverseTree(newRoot,curDelta);
        return curDelta;
    }
}
