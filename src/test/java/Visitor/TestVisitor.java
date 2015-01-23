import org.junit.*;
import static org.junit.Assert.*;

public class TestVisitor{
	
	private class TestComponentNodeVisitor implements ComponentNodeVisitor {
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
	public void testTestVisitor(){
		assertVisitTestVisitor(new NumberLeafNode("2"));
		assertVisitTestVisitor(new LeftParenthesisLeafNode());
		assertVisitTestVisitor(new RightParenthesisLeafNode());
		assertVisitTestVisitor(new AdditionCompositeBinaryNode(
			new NumberLeafNode("2"), new NumberLeafNode("3")));
		assertVisitTestVisitor(
			new NegationCompositeUnaryNode( new NumberLeafNode("2")));
		
	}
	
	
	private void assertVisitTestVisitor(ComponentNode node){
		TestComponentNodeVisitor visitor =
				new TestComponentNodeVisitor();
		visitor.visit(node);
		assertTrue("Attempt to visit node " 
			+ node.toString() + " failed.",
			visitor.hasBeenVisited);
	}
	
	
	@Test
	public void testPrintVisitor(){
		fail("method needs implementation.");
	}
	
	@Test
	public void testEvaluateVisitor(){
		fail("method needs implementation.");
	}
		
}
