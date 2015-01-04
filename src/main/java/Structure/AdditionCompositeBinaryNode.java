public class AdditionCompositeBinaryNode extends CompositeBinaryNode {
		
	public AdditionCompositeBinaryNode(ComponentNode leftNode, ComponentNode rightNode){
		super(leftNode, rightNode);
	}
	
	public String getItem(){
		return "+";
	}
}
