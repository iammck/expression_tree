public class RightParenthesisLeafNode extends LeafNode {
	public RightParenthesisLeafNode(){
		super(")");
	}
		
	public int compareToNode(ComponentNode node){
		// equals to other left right parenthesis leaf nodes
		if ((node instanceof LeftParenthesisLeafNode)){
			return 0;
		} else if ((node instanceof RightParenthesisLeafNode)){
			return 0;
		}
		// Is greater than all other nodes.
		return 1;
	}
}
