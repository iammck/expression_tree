import java.util.*;

public class ExpressionTree implements Iterable<ComponentNode> {
	
	ComponentNode root;
	ExpressionTreeContext context;
	
	private ExpressionTree(){
	}
	
	public ExpressionTree(ExpressionTreeContext context, ComponentNode root){
		if (context == null || root == null){
			throw new IllegalArgumentException(
				"ExpressionTree constructor requires "
				+ "valid ExressionTreeContext and ComponentNode.");
		}
		this.root = root;
		this.context = context;
	}
	
	public ComponentNode getRoot(){
		return root;
	}
	
	public Iterator<ComponentNode> iterator(){
		// want an iterator to match the current traversal order
		ExpressionTreeContext.TreeOrder order = context.getCurrentTreeOrder();
		if (order == null){
			throw new NullPointerException(
				"Context must have a valid tree order.");
		}			
		switch (order){
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
				+ "using current tree order " + order + ".");
		}
	}
	
	public void accept(ComponentNodeVisitor componentNodeVisitor){
		//componentNodeVisitor.respondToVisit();
		
	}
}
