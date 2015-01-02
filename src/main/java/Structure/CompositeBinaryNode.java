public abstract class CompositeBinaryNode extends CompositeUniaryNode {
	
	private ComponentNode leftChild;
	
	public CompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode);
		this.leftNode = leftNode;
	}
	
	ComponentNode getLeftChild(){
		return leftChild;
	}	
}

