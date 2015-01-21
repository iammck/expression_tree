import org.junit.*;
import static org.junit.Assert.*;

public class TestComponentNodeVisitor{
	
	private class ConcreteComponentNodeVisitor implements ComponentNodeVisitor {
		boolean hasBeenVisited = false;
		ComponentNode node = null;
		
		public void respondToVisit(ComponentNode node){
		 hasBeenVisited = true;			
		}
		
		public void visit(){
			node.accept(this);
		}
		
		public void visit(ComponentNode node){
			this.node = node;
			this.visit();
		}
	}
	
	@Test
	public void testVisitor(){
		assertVisitComponentNode(new NumberLeafNode("2"));
		assertVisitComponentNode(new LeftParenthesisLeafNode());
		assertVisitComponentNode(new RightParenthesisLeafNode());
		assertVisitComponentNode(new AdditionCompositeBinaryNode(
			new NumberLeafNode("2"), new NumberLeafNode("3")));
		assertVisitComponentNode(
			new NegationCompositeUnaryNode( new NumberLeafNode("2")));
		
	}
	
	
	private void assertVisitComponentNode(ComponentNode node){
		ConcreteComponentNodeVisitor visitor =
				new ConcreteComponentNodeVisitor();
		visitor.visit(node);
		assertTrue("Attempt to visit node " 
			+ node.toString() + " failed.",
			visitor.hasBeenVisited);
	}
		
		
}
