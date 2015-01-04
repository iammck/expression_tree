public class LeafNode implements ComponentNode {
	
	private String item;
	
	public LeafNode(String item){
		this.item = item;
	}
	
	public String getItem(){
		return item;	
	}
	
	public ComponentNode getLeftChild(){
		return null;
	}
	
	public ComponentNode getRightChild(){
		return null;
	}
}
