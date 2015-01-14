public interface ComponentNode {
	String getItem();
	ComponentNode getLeftChild();
	ComponentNode getRightChild();	
	int compareToNode(ComponentNode node);
	
}

