public abstract class CompositeBinaryNode extends CompositeUnaryNode {
	
	private ComponentNode leftChild;
	
	public CompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(rightNode);
		leftChild = leftNode;
	}
	
	public ComponentNode getLeftChild(){
		return leftChild;
	}	
}

