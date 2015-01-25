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
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7 * (8 *( 12 + 4)))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");		
		assertInOrderIteratorResult("2","2");
		assertInOrderIteratorResult("-2","-2");

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
		assertPreOrderIteratorResult("4*5+6", "+*456");
		// no difference in next two because infix interpreter
		assertPreOrderIteratorResult("4+5+7", "++457");
		assertPreOrderIteratorResult("(4+5)+7", "++457");
		// but the interpreter does create a diff tree as below.
		assertPreOrderIteratorResult("4+(5+7)", "+4+57");
		assertPreOrderIteratorResult("(4+5)*(6+2)", "*+45+62");
		assertPreOrderIteratorResult("4*(1+3)*(6+2)", "**4+13+62");

		assertPreOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			            	    "*+45*7*4+57");
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPreOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "*+45*7*4+5**78+124");
		assertPreOrderIteratorResult("(4+5)*(7*(4*(5+7 *( 8 *( 12 + 4)))))", 
			             "*+45*7*4+5*7*8+124");		
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPreOrderIteratorResult("(5+7 * 8 *( 12 + 4))", 
			             "+5**78+124");		
		assertPreOrderIteratorResult("(5+7 *( 8 *( 12 + 4)))", 
			             "+5*7*8+124");
		assertPreOrderIteratorResult("2","2");
		assertPreOrderIteratorResult("-2","-02");
	}
	
	@Test
	public void testPostOrderIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.
		assertPostOrderIteratorResult("4+1", "41+");
		assertPostOrderIteratorResult("4+5*2", "452*+");
		assertPostOrderIteratorResult("(4+5)*3", "45+3*");
		assertPostOrderIteratorResult("4*5+4", "45*4+");
		assertPostOrderIteratorResult("4*(5+5)", "455+*");
		assertPostOrderIteratorResult("4*5+6", "45*6+");
		// no difference in next two because infix interpreter
		assertPostOrderIteratorResult("4+5+7", "45+7+");
		assertPostOrderIteratorResult("(4+5)+8", "45+8+");
		// but the interpreter does create a diff tree as below.
		assertPostOrderIteratorResult("4+(5+9)", "459++");
		assertPostOrderIteratorResult("(4+5)*(6+2)", "45+62+*");
		assertPostOrderIteratorResult("4*(1+3)*(6+2)", "413+*62+*");

		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			            	      "45+7457+***");
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             	      "45+74578*124+*+***");
		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7 *( 8 *( 12 + 4)))))", 
			                      "45+74578124+**+***");		
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostOrderIteratorResult("(5+7 * 8 *( 12 + 4))", 
			             	      "578*124+*+");		
		assertPostOrderIteratorResult("(5+7 *( 8 *( 12 + 4)))", 
			             	      "578124+**+");
		assertPostOrderIteratorResult("2","2");
		assertPostOrderIteratorResult("-2","02-");
		assertPostOrderIteratorResult("5+-6","506-+");
		

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
