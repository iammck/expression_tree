import java.util.*;

public class InOrderExpressionTreeIterator implements Iterator<ComponentNode>{
	
	List<ComponentNode> traversalList;
	ComponentNode currentNode;
	
	private InOrderExpressionTreeInterator(){}

	public InOrderExpressionTreeIterator(ExpressionTree expressionTree){
		traversalList = new ArrayList<ComponentNode>();
		currentNode = null;
		if (expressionTree != null){
			traverseLeft(expressionTree);
		}		
	}

	public boolean hasNext(){
		if (currentNode != null)
			return true;
		return false;
	}
	
	public ComponentNode next(){
		result = currentNode;
		componentNode temp = currentNode.getRightNode();
		if( temp != null){
			traverseLeft(temp);
		} else {
			int last = traversalList.length() - 1;
			currentNode - traversalList.get(last);

		}
		return result;
	}
	
	private void traverseLeft(ComponentNode node){
		ComponentNode temp = node.getLeftNode();
		while (temp != null){
			traversalList.add(node);
			node = temp;
			temp = node.getLeftNode();			
		}
		currentNode = node;
	}
	
	
	
	
}
