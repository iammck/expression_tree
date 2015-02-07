import java.util.*;

public class PostFixIterator implements Iterator<ComponentNode>{
	
	
	private enum NodeState{ left, right};
	
	private class PendingNode {
		ComponentNode componentNode;
		NodeState nodeState;
		
		PendingNode(ComponentNode componentNode){
			this.componentNode = componentNode;
			this.nodeState = NodeState.left;
		}
	}
	
	List<PendingNode> pendingList;
	ComponentNode current;
	
	private PostFixIterator(){}
	
	public PostFixIterator(ExpressionTree expressionTree){
		pendingList = new ArrayList<PendingNode>();
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
		// else get the last pending node
		int last = pendingList.size() - 1; 
		PendingNode pendingNode = pendingList.get(last);
		switch (pendingNode.nodeState){
		case left:// went left last time, go right, then left.
			pendingNode.nodeState = NodeState.right;
			current = pendingNode.componentNode.getRightChild();
			goLeft();
			break;
		case right:// went right last time. go up. and remove the node.
			current = pendingNode.componentNode;
			pendingList.remove(pendingNode);
			break;
		}
	}
	
	private void goLeft(){
		handleNegationNode();
		ComponentNode child = current.getLeftChild();
		while (child != null){
			// Get a pending node, node state is left.
			PendingNode node = new PendingNode(current);
			pendingList.add(node);
			// Having added the current to list, set current to
			current = child;
			handleNegationNode();
			child = current.getLeftChild();
		}
	}
	
	private void handleNegationNode(){
		if (!(current instanceof NegationCompositeUnaryNode)){
			return;
		}		
		// Will need a NumberLeafNode with "0" as left.
		NumberLeafNode zero = new NumberLeafNode("0");
		// set current as a subtraction binary node
		current = new SubtractionCompositeBinaryNode(
				zero, // with the right parameters.
				current.getRightChild());
	}
}
