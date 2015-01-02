public class MinusCompositeBinaryNode extends CompositeBinaryNode {
		
	public CompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode, leftNode);
	}
	
	public int getItem(){
		return '-';
	}
}
