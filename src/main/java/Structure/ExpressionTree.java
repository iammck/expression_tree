public class ExpressionTree implements ComponentNode {
	
	ComponentNode root;
	
	public ExpressionTree(ComponentNode root){
		this.root = root;
	}
	
	public String getItem(){
		return root.getItem();
	}
	
	public ComponentNode getRightChild(){
		return root.getRightChild();
	}
	
	public ComponentNode getLeftChild(){
		return root.getLeftChild();
	}
	
	public int compareToNode(ComponentNode node){
		return root.compareToNode(node);
	}
}
