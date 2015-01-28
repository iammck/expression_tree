import java.util.*;

public class PreFixIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PreFixIterator(){}
	
	public PreFixIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree.getRoot();
	}

	public boolean hasNext(){
		return (current != null)? true:false;
	}
	
	public ComponentNode next(){
		ComponentNode result = current;
		getNext();
		return result;
	}
	
	private void getNext(){
		if(!goLeft())
			goRight();
	}
	
	private boolean goLeft(){
		ComponentNode child = current.getLeftChild();
		if(child != null){
			pendingList.add(current);
			current = child;
			return true;
		// if current is unary, it will have a right, 
		} else if ( current.getRightChild() != null){
			// Will need a NumberLeafNode with "0" as left.
			pendingList.add(current);
			current = new NumberLeafNode("0");
			return true;
		} else {
			return false;
		}
	}
	
	private void goRight(){
		int last = pendingList.size() - 1;
		if (last >= 0){
			ComponentNode node = pendingList.remove(last);
			current = node.getRightChild();
		} else {
			current = null;
		}			
	}
}
