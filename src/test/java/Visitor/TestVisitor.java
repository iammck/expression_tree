import org.junit.*;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.io.*;


public class TestVisitor{
	
	private class TestComponentNodeVisitor implements ComponentNodeVisitor {
		boolean hasBeenVisited = false;
		ComponentNode node = null;
		
		public void visit(ComponentNode node){
		 hasBeenVisited = true;
		 this.node = node;
		}
	}
	
	@Test
	public void testTestVisitorIsVisitedByNode(){
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
		node.accept(visitor);
		assertTrue("Attempt to visit node " 
			+ node.toString() + " failed.",
			visitor.hasBeenVisited);
	}
		
	@Test
	public void testPrintVisitor(){
		assertInfixInOrderPrintVisitorEquals("3","3");
		assertInfixInOrderPrintVisitorEquals("3+3","3+3");
		assertInfixInOrderPrintVisitorEquals("(3+3)*6","3+3*6");
		assertInfixPreOrderPrintVisitorEquals("3","3");
		assertInfixPreOrderPrintVisitorEquals("3+3","+33");
		assertInfixPreOrderPrintVisitorEquals("(3+3)*6","*+336");

	}
	
	private void assertInfixInOrderPrintVisitorEquals(String input, String output){
		try {
			// set the system out to something that can be checked.
			PrintStream printStream = new PrintStream("bin\\PrintVisitor.log");
			PrintStream original = System.out;
			System.setOut(printStream);
			// get a printvisitor
			ComponentNodeVisitor printVisitor = new PrintVisitor();
			// get an expression tree for the input
			ExpressionTree expTree = getInfixInOrderExpressionTree(input);
			// iterater through
			for(ComponentNode node: expTree){
				node.accept(printVisitor);
			}
			// close the printStream and set out ot original.
			// check the the out put is correct.					
			System.setOut(original);
			printStream.close();
			BufferedReader reader = new BufferedReader(
					new FileReader("bin\\PrintVisitor.log"));
			String result = reader.readLine();
			assertEquals("The input " + input + " did not result in "
				+ output + ", but was " + result ,
				output, result);
			// close the reader.
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to create PrintStream for PrintVisiter.log.");
		} catch (IOException e){
			System.out.println("Unable to create BufferedReader for PrintVisiter.log.");
		}
	}
	
	private void assertInfixPreOrderPrintVisitorEquals(String input, String output){
		try {
			// set the system out to something that can be checked.
			PrintStream printStream = new PrintStream("bin\\PrintVisitor.log");
			PrintStream original = System.out;
			System.setOut(printStream);
			// get a printvisitor
			ComponentNodeVisitor printVisitor = new PrintVisitor();
			// get an expression tree for the input
			ExpressionTree expTree = getInfixPreOrderExpressionTree(input);
			// iterater through
			for(ComponentNode node: expTree){
				node.accept(printVisitor);
			}
			// close the printStream and set out ot original.
			// check the the out put is correct.					
			System.setOut(original);
			printStream.close();
			BufferedReader reader = new BufferedReader(
					new FileReader("bin\\PrintVisitor.log"));
			String result = reader.readLine();
			assertEquals("The input " + input + " did not result in "
				+ output + ", but was " + result ,
				output, result);
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to create PrintStream for PrintVisiter.log.");
		} catch (IOException e){
			System.out.println("Unable to create BufferedReader for PrintVisiter.log.");
		}
	}
	
	private void assertPrefixPostOrderPrintVisitorEquals(String input, String output){
		try {
			// set the system out to something that can be checked.
			PrintStream printStream = new PrintStream("bin\\PrintVisitor.log");
			PrintStream original = System.out;
			System.setOut(printStream);
			// get a printvisitor
			ComponentNodeVisitor printVisitor = new PrintVisitor();
			// get an expression tree for the input
			ExpressionTree expTree = getInfixPreOrderExpressionTree(input);
			// iterater through
			for(ComponentNode node: expTree){
				node.accept(printVisitor);
			}
			// close the printStream and set out ot original.
			// check the the out put is correct.					
			System.setOut(original);
			printStream.close();
			BufferedReader reader = new BufferedReader(
					new FileReader("bin\\PrintVisitor.log"));
			String result = reader.readLine();
			assertEquals("The input " + input + " did not result in "
				+ output + ", but was " + result ,
				output, result);
			reader.close();
		} catch (FileNotFoundException e){
			System.out.println("Unable to create PrintStream for PrintVisiter.log.");
		} catch (IOException e){
			System.out.println("Unable to create BufferedReader for PrintVisiter.log.");
		}
	}
	
	private ExpressionTree getInfixInOrderExpressionTree(String input){
		// create the expression tree
		ExpressionTreeContext ec = new ExpressionTreeContext();
		ec.setCurrentTraversalOrder(ExpressionTreeContext.TraversalOrder.inorder);
		InterpreterContext ic = new InterpreterContext();
		ic.setExpressionTreeContext(ec);
		Interpreter interpreter = new InfixInterpreter();
		ExpressionTree expTree = interpreter.interpret(ic, input);
		return expTree;
	}
	
	private ExpressionTree getInfixPreOrderExpressionTree(String input){
		// create the expression tree
		ExpressionTreeContext ec = new ExpressionTreeContext();
		ec.setCurrentTraversalOrder(ExpressionTreeContext.TraversalOrder.preorder);
		InterpreterContext ic = new InterpreterContext();
		ic.setExpressionTreeContext(ec);
		Interpreter interpreter = new InfixInterpreter();
		ExpressionTree expTree = interpreter.interpret(ic, input);
		return expTree;
	}
	
	
	private ExpressionTree getInfixPostOrderExpressionTree(String input){
		// create the expression tree
		ExpressionTreeContext ec = new ExpressionTreeContext();
		ec.setCurrentTraversalOrder(ExpressionTreeContext.TraversalOrder.postorder);
		InterpreterContext ic = new InterpreterContext();
		ic.setExpressionTreeContext(ec);
		Interpreter interpreter = new InfixInterpreter();
		ExpressionTree expTree = interpreter.interpret(ic, input);
		return expTree;
	}
	
	@Test
	public void testEvaluateVisitor(){
		fail("method needs implementation.");
	}
		
}
