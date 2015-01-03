public class MultiplyCompositeBinaryNode extends CompositeBinaryNode {
		
	public MultiplyCompositeBinaryNode(ComponentNode rightNode, ComponentNode leftNode){
		super(rightNode, leftNode);
	}
	
	public int getItem(){
		return '*';
	}
}
