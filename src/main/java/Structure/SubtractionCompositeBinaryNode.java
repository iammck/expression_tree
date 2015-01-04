public class SubtractionCompositeBinaryNode extends CompositeBinaryNode {
	public SubtractionCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "-";
	}
}
