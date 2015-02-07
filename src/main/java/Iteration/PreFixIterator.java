import java.util.*;

public class PreFixIterator implements Iterator<ComponentNode>{
	List<ComponentNode> pendingList;
	ComponentNode current;
	
	private PreFixIterator(){}
	
	public PreFixIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<ComponentNode>();
		current = expressionTree.getRoot();
		if (current instanceof NegationCompositeUnaryNode)
			convertToSubtraction();
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
			if (current instanceof NegationCompositeUnaryNode)
				convertToSubtraction();
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
			if (current instanceof NegationCompositeUnaryNode)
				convertToSubtraction();
		} else {
			current = null;
		}			
	}
	
	private void convertToSubtraction(){
		// Will need a NumberLeafNode with "0" as left.
		NumberLeafNode zero = new NumberLeafNode("0");
		// set current as a subtraction binary node
		current = new SubtractionCompositeBinaryNode(
				zero, // with the right parameters.
				current.getRightChild());
	}
		
}
