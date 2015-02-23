import java.util.*;

public class ExpressionTree implements Iterable<ComponentNode> {
	
	
	// TreeOrder and InputFormat
	public enum TraversalOrder {
		preorder, inorder, postorder,
		prefix, infix, postfix 		};
	
	
	ComponentNode root;
	ExpressionTreeContext context;
	TraversalOrder traversalOrder;
	
	private ExpressionTree(){
	}
	
	public ExpressionTree(TraversalOrder traversalOrder, ComponentNode root){
		if (traversalOrder == null || root == null){
			throw new IllegalArgumentException(
				"ExpressionTree constructor requires "
				+ "valid ExressionTreeContext and ComponentNode.");
		}
		this.root = root;
		this.traversalOrder = traversalOrder;
	}
	
	public ComponentNode getRoot(){
		return root;
	}
	
	public TraversalOrder getTraversalOrder(){
		return traversalOrder;
	}
	
	public void setTraversalOrder(String traversalOrder) throws InvalidInputException{
		try {	
			setTraversalOrder(Enum.valueOf(
				TraversalOrder.class, traversalOrder));
		} catch (Exception e) {
			throw new InvalidInputException(traversalOrder
				+ " is not a valid expression tree traversal order.");
		}	
	}
	
	public void setTraversalOrder(TraversalOrder traversalOrder){
		this.traversalOrder = traversalOrder;
	}
	
	public Iterator<ComponentNode> iterator(){
		// want an iterator to match the current traversal order
		switch (traversalOrder){
		case preorder:
			return new PreOrderIterator(this);
		case postorder:
			return new PostOrderIterator(this);
		case inorder:
			return new InOrderIterator(this);			
		case prefix:
			return new PreFixIterator(this);
		case postfix:
			return new PostFixIterator(this);
		case infix:
			return new InFixIterator(this);			
		default:
			throw new IllegalArgumentException( "Can not make an iterator "
				+ "using current tree order " + traversalOrder + ".");
		}
	}
	
	public void accept(ComponentNodeVisitor componentNodeVisitor){
		//componentNodeVisitor.respondToVisit();
		
	}
}
