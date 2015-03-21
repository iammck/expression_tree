public class ExponentialCompositeBinaryNode extends CompositeBinaryNode {

	public ExponentialCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "^";
	}
	
	public int compareToNode(ComponentNode node){
		// equal to other Multiplication or Division nodes.
		if ((node instanceof ExponentialCompositeBinaryNode)){
			return 0;
		// less than negaition and parenthesis
		} else if ((node instanceof NegationCompositeUnaryNode)){
			return -1;
		} else if ((node instanceof LeftParenthesisLeafNode)){
			return -1;
		} else if ((node instanceof RightParenthesisLeafNode)){
			return -1;
		}
		// Is greater than all other nodes.
		return 1;
	}


}
