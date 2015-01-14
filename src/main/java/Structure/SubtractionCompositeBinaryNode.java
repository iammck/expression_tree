public class SubtractionCompositeBinaryNode extends CompositeBinaryNode {
	public SubtractionCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "-";
	}
	
	public int compareToNode(ComponentNode node){
		if ((node instanceof AdditionCompositeBinaryNode)){
			return 0;
		} else if ((node instanceof SubtractionCompositeBinaryNode)){
			return 0;
		} else if ((node instanceof NumberLeafNode)){
			return 1;
		}
		// Otherwise this node is less than any other node.
		return -1;
	}
}
