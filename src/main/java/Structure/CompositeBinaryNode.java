public abstract class CompositeBinaryNode implements ComponentNode {
	
	private ComponentNode leftChild;
	private ComponentNode rightChild;
	
	public CompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		leftChild = leftNode;
		rightChild = rightNode;
	}
	
	public ComponentNode getLeftChild(){
		return leftChild;
	}

	public ComponentNode getRightChild(){
		return rightChild;
	}
	
	public void accept(ComponentNodeVisitor componentNodeVisitor){
		componentNodeVisitor.visit(this);
	}	
}

