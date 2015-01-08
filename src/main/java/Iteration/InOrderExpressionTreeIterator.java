import java.util.*;

public class InOrderExpressionTreeIterator implements Iterator<ComponentNode>{

	// TODO This class must some how add parenthesis	
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private InOrderExpressionTreeIterator(){}
	
	public InOrderExpressionTreeIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree;
		goLeft();
	}

	public boolean hasNext(){
		if (current != null)
			return true;
		return false;
	}
	
	public ComponentNode next(){
		ComponentNode result = current;
		if(current.getRightChild() != null)
			goRight();
		else
			current = pendingList.remove(0);
		return result;
	}
	
	private void goRight(){
		current = current.getRightChild();
		if (current.getLeftChild() != null)
			goLeft();
	}
	
	private void goLeft(){
		while (current != null){
			// if has a left child, add the ( ) to pending when needed
			boolean hasLeftChild = 
				(current.getLeftChild() != null) ? true: false;
			if (hasLeftChild){
				int rightParPos = lastIndexOfRightParenthesis() + 1; 
				pendingList.add(rightParPos, new LeafNode(")"));
			}
			// add the current to pending
			int curPos = lastIndexOfRightParenthesis() + 1;
			pendingList.add(curPos, current);
			// if has a left child the the ( to pending after.
			if (hasLeftChild){
				int leftParPos = lastIndexOfRightParenthesis() + 1; 
				pendingList.add(leftParPos, new LeafNode("("));
			}
			// next
			current = current.getLeftChild();
		}
		// if there is any pending, current is the first pending.
		if (pendingList.size() > 0)
			current = pendingList.remove(0); // remove it!
	}
	
	private int lastIndexOfRightParenthesis(){
		int result = -1;
		int pendingListSize = pendingList.size(); 
		if (pendingListSize == 0){
			return result;
		}
		int index = 0;
		// get the first as next for entrance into a while loop
		ComponentNode next = pendingList.get(index);
		while(next.getItem().equals("(")){
			result = index;
			index += 1;
			if (index  >= pendingListSize){
				return result;
			}			
			next = pendingList.get(index);		
		}
		return result;
	}		
}
