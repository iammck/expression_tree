public class NegationCompositeUnaryNode extends CompositeUnaryNode {

	public NegationCompositeUnaryNode(ComponentNode rightChild){
		super(rightChild);
	}
	
	public int getItem(){
		return '-';	
	}
}

