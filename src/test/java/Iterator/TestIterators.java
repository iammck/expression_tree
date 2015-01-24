import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;


public class TestIterators {	
	@Test
	public void testInOrderIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration which does not always 
		// match input because redundant parenthesis are removed.
		assertInOrderIteratorResult("4+1", "4+1");
		assertInOrderIteratorResult("4+5*2", "4+5*2");
		assertInOrderIteratorResult("(4+5)*3", "(4+5)*3");
		assertInOrderIteratorResult("4*5+4", "4*5+4");
		assertInOrderIteratorResult("4*(5+5)", "4*(5+5)");
		assertInOrderIteratorResult("4*5+6", "4*5+6");
		assertInOrderIteratorResult("4+5+7", "4+5+7");
		assertInOrderIteratorResult("(4+5)*(6+2)", "(4+5)*(6+2)");
		assertInOrderIteratorResult("4*(1+3)*(6+2)", "4*(1+3)*(6+2)");
		// notice, the iterator does simplify parenthesis.
		assertInOrderIteratorResult("(4+5)+7", "4+5+7");
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			             "(4+5)*7*4*(5+7)");
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");		
	}
	
	@Test
	public void testPreOrderIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.
		assertPreOrderIteratorResult("4+1", "+41");
		assertPreOrderIteratorResult("4+5*2", "+4*52");
		assertPreOrderIteratorResult("(4+5)*3", "*+453");
		assertPreOrderIteratorResult("4*5+4", "+*454");
		assertPreOrderIteratorResult("4*(5+5)", "*4+55");
		assertPreOrderIteratorResult("4*5+6", "*+456");
		assertPreOrderIteratorResult("4+5+7", "++457");
		assertPreOrderIteratorResult("(4+5)*(6+2)", "*+45+62");
		assertPreOrderIteratorResult("4*(1+3)*(6+2)", "**4+13+62");

		assertPreOrderIteratorResult("(4+5)+7", "++457");
		assertPreOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			            	    "*+45*7*4+57");
		assertPreOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "*+45*7*4+5*7*8+124");		
	}
	
	@Test
	public void testPostOrderIterator(){
		fail("method needs to be implemented.");
	}	
	
	private void assertInOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expressionTree
		Iterator<ComponentNode> iterator = new InOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private void assertPreOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PreOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}

	private void assertPostOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PostOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private ExpressionTree getExpressionTree(String input){
		// create an expression tree from input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new InfixInterpreter();
		ExpressionTree expTree = interpreter.interpret(context, input);
		assertNotNull("Unable to get expression tree from input "
			+ input + ".", expTree);		
		return expTree;
	}
	
	private void assertIteratorEquals(String expected, Iterator<ComponentNode> iterator){
		StringBuilder resultBuilder = new StringBuilder();
		while(iterator.hasNext()){
			ComponentNode node = iterator.next();
			resultBuilder.append(node.getItem());	
			
		}
		assertEquals("iterator " + iterator.toString() 
			+ " did not result in " + expected 
			+ ", but was " + resultBuilder.toString(),
					expected, resultBuilder.toString());		
	}

}
