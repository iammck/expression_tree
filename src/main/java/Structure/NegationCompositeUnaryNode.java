public class NegationCompositeUnaryNode extends CompositeUnaryNode {
	public NegationCompositeUnaryNode(ComponentNode rightNode){
		super(rightNode);
	}
	
	public String getItem(){
		return "-";	
	}
	
	public int compareToNode(ComponentNode node){
		// equal to itself
		if ((node instanceof NegationCompositeUnaryNode)){
			return 0;
		// less than left and right parethenesis leaf nodes.
		} else if ((node instanceof LeftParenthesisLeafNode)){
			return -1;
		} else if ((node instanceof RightParenthesisLeafNode)){
			return -1;
		}
		// Is greater than all other nodes.
		return 1;
	}	
}

