public class ExpressionTree implements ComponentNode {
	
	ComponentNode root;
	
	private ExpressionTree(){
	}
	
	public ExpressionTree(ComponentNode root){
		if (root == null){
			throw new IllegalArgumentException(
				"ExpressionTree constructor requires "
				+ "valid ComponentNode.");
		}
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
