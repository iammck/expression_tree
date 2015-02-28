import java.util.*;

public class PreOrderIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PreOrderIterator(){}
	
	public PreOrderIterator(ExpressionTree expressionTree){
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
		} else {
			return false;
		}
	}
	
	private void goRight(){
		ComponentNode child = current.getRightChild();
		int last = pendingList.size() - 1;
		if(child != null){
			current = child;
		} else if (last >= 0){
			ComponentNode node = pendingList.remove(last);
			current = node.getRightChild();
		} else {
			current = null;
		}			
	}
}
