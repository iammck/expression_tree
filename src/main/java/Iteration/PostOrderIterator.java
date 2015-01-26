import java.util.*;

public class PostOrderIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PostOrderIterator(){}
	
	public PostOrderIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree.getRoot();
		goLeft();
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
		// else get the last node and its right child node.
		int last = pendingList.size() - 1;
		ComponentNode node = pendingList.get(last);
		ComponentNode child = node.getRightChild();
		if (current != child){
			current = child;
			goLeft();
		} else {
			current = node;
			pendingList.remove(node);
		}
	}
	
	private void goLeft(){
		ComponentNode child = current.getLeftChild();
		while (child != null){
			// add the current to pending.
			pendingList.add(current);
			current = child;
			child = current.getLeftChild();
		}
		// if current is a unary node will have a right child,
		if (current.getRightChild() != null){
		// need to add a NumberLeafNode with a "0" item
			pendingList.add(current);
			current = new NumberLeafNode("0");
		}
	}
}
