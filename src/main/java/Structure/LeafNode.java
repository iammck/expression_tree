public class LeafNode implements ComponentNode {
	
	private int item;
	
	public LeafNode(int item){
		this.item = item;
	}
	
	public int getItem(){
		return item;	
	}
	
	public ComponentNode getLeftChild(){
		return null;
	}
	
	public ComponentNode getRightChild(){
		return null;
	}
}
