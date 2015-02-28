import java.util.*;

public class InOrderIterator implements Iterator<ComponentNode>{

	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private InOrderIterator(){}
	
	public InOrderIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree.getRoot();
		goLeft();
	}

	public boolean hasNext(){
		if (current != null)
			return true;
		return false;
	}
	
	public ComponentNode next(){
		ComponentNode result = current;
		getNext();
		return result;
	}
	
	private void getNext(){
		if(current.getRightChild() != null){
			goRight();
		} else if (!pendingList.isEmpty()) {
			current = pendingList.remove(0);
		} else {
			current = null;
		}
	}
	
	private void goRight(){
		current = current.getRightChild();
		if (current.getLeftChild() != null)
			goLeft();
	}
	
	private void goLeft(){
		while (current != null){
			ComponentNode child = current.getLeftChild();
			// add the current to pending as first.
			pendingList.add(0, current);
			// next current.
			current = current.getLeftChild();
		}
		// if there is any pending, current is the first pending.
		if (pendingList.size() > 0)
			current = pendingList.remove(0); // remove it!
	}
}
