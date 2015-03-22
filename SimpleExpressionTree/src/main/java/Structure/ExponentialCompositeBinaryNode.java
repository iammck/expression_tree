public class ExponentialCompositeBinaryNode extends CompositeBinaryNode {

	public ExponentialCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "^";
	}
	
	public int compareToNode(ComponentNode node){
		// less than negaition and parenthesis
		if ((node instanceof NegationCompositeUnaryNode)){
			return -1;
		} else if ((node instanceof LeftParenthesisLeafNode)){
			return -1;
		} else if ((node instanceof RightParenthesisLeafNode)){
			return -1;
		}
		// Is greater than all other nodes including other expo.
		return 1;
	}


}
