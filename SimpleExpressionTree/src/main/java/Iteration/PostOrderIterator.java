import java.util.*;

public class PostOrderIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PostOrderIterator(){}
	
	public PostOrderIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree.getRoot();
		goRight();
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
		// nothing pending? done.
		if (pendingList.isEmpty()){
			current = null;
			return;
		}
		// else get the last node and its left child node.
		int last = pendingList.size() - 1;
		ComponentNode node = pendingList.get(last);
		ComponentNode child = node.getLeftChild();
		// if there is a left child and it is not current
		if (child != null && current != child){
			current = child;
			goRight();
		} else {
			current = node;
			pendingList.remove(node);
		}
	}
	
	private void goRight(){
		ComponentNode child = current.getRightChild();
		while (child != null){
			// add the current to pending.
			pendingList.add(current);
			current = child;
			child = current.getRightChild();
		}
	}
}
