public abstract class CompositeBinaryNode extends CompositeUnaryNode {
	
	private ComponentNode leftChild;
	
	public CompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode);
		leftChild = leftNode;
	}
	
	public ComponentNode getLeftChild(){
		return leftChild;
	}	
}

