public abstract class CompositeUnaryNode implements ComponentNode {
	
	private ComponentNode rightChild;
	
	public CompositeUnaryNode(ComponentNode rightChild){
		this.rightChild = rightChild;
	}
	
	ComponentNode getRightChild(){
		return rightChild;
	}
}
