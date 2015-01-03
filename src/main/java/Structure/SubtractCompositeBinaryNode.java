public class SubtractCompositeBinaryNode extends CompositeBinaryNode {
	public SubtractCompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode, leftNode);
	}
	
	public int getItem(){
		return '-';
	}
}
