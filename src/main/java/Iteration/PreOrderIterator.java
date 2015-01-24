import java.util.*;

public class PreOrderIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PreOrderIterator(){}
	
	public PreOrderIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree;
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
	}
	
	private void goRight(){
	}
	
	private void goLeft(){
	}
	

}
