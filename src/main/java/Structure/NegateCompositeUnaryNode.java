public class NegateCompositeUnaryNode extends CompositeUnaryNode {
	public NegateCompositeUnaryNode(ComponentNode rightChild){
		super(rightChild);
	}
	
	public int getItem(){
		return '-';	
	}
}

