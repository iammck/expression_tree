public abstract class CompositeUnaryNode implements ComponentNode {
	
	private ComponentNode rightChild;
	
	public CompositeUnaryNode(ComponentNode rightNode){
		rightChild = rightNode;
	}
	
	public ComponentNode getLeftChild(){
		return null;
	}
	
	public ComponentNode getRightChild(){
		return rightChild;
	}
	
	public void accept(ComponentNodeVisitor componentNodeVisitor){
		componentNodeVisitor.visit(this);
	}
}
