public class NegationCompositeUnaryNode extends CompositeUnaryNode {
	public NegationCompositeUnaryNode(ComponentNode rightNode){
		super(rightNode);
	}
	
	public String getItem(){
		return "-";	
	}
}

