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
		switch (order){
		case preorder:
			return new PreOrderIterator(this);
		case postorder:
			return new PostOrderIterator(this);
		case inorder:
			return new InOrderIterator(this);			
		};
		return null;
	}
	
	public void accept(ComponentNodeVisitor componentNodeVisitor){
		//componentNodeVisitor.respondToVisit();
		
	}
}
