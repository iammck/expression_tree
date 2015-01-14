import java.util.*;

public class InOrderExpressionTreeIterator implements Iterator<ComponentNode>{

	List<ComponentNode> pendingList;
	ComponentNode current;
	ComponentNode preveous;
	
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
			// Determine if needing parenthesis before decent
			boolean needsParenthesis = false; 
			ComponentNode child = current.getLeftChild();
			// if left child exists and current has greater node value
			if (child != null && current.compareToNode(child) > 0){
				needsParenthesis = true;
			}
			if (needsParenthesis){
				int rightParPos = lastIndexOfLeftParenthesis() + 1; 
				pendingList.add(rightParPos, new RightParenthesisLeafNode());
			}
			// add the current to pending
			int curPos = lastIndexOfLeftParenthesis() + 1;
			pendingList.add(curPos, current);
			// if has a left child the the ( to pending after.
			if (needsParenthesis){
				int leftParPos = lastIndexOfLeftParenthesis() + 1; 
				pendingList.add(leftParPos, new LeftParenthesisLeafNode());
			}
			// next
			current = current.getLeftChild();
		}
		// if there is any pending, current is the first pending.
		if (pendingList.size() > 0)
			current = pendingList.remove(0); // remove it!
	}
	
	private int lastIndexOfLeftParenthesis(){
		int result = -1;
		int pendingListSize = pendingList.size(); 
		if (pendingListSize == 0){
			return result;
		}
		int index = 0;
		// get the first as next for entrance into a while loop
		ComponentNode next = pendingList.get(index);
		while(next instanceof LeftParenthesisLeafNode){
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
