import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

// the ones with fix are used for expression building where as the other three are for
// expression tree checking.

public class TestIterators {	
	@Test
	public void testInOrderIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration which does not always 
		// match input because redundant parenthesis are removed.
		assertInOrderIteratorResult("4+1", "4+1");
		assertInOrderIteratorResult("4+5*2", "4+5*2");
		assertInOrderIteratorResult("(4+5)*3", "4+5*3");
		assertInOrderIteratorResult("4*5+4", "4*5+4");
		assertInOrderIteratorResult("4*(5+5)", "4*5+5");
		assertInOrderIteratorResult("4*5+6", "4*5+6");
		assertInOrderIteratorResult("4+5+7", "4+5+7");
		assertInOrderIteratorResult("(4+5)*(6+2)", "4+5*6+2");
		assertInOrderIteratorResult("4*(1+3)*(6+2)", "4*1+3*6+2");
		// notice, the iterator does simplify parenthesis.
		assertInOrderIteratorResult("(4+5)+7", "4+5+7");
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			             "4+5*7*4*5+7");
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "4+5*7*4*5+7*8*12+4");		
		assertInOrderIteratorResult("(4+5)*(7*(4*(5+7 * (8 *( 12 + 4)))))", 
			             "4+5*7*4*5+7*8*12+4");		
		assertInOrderIteratorResult("2","2");
		assertInOrderIteratorResult("-2","-2");
	}
	
	@Test
	public void testInfixIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration which does not always 
		// exactly match input because redundant parenthesis are removed.
		assertInfixIteratorResultEquals("4+1", "4+1");
		assertInfixIteratorResultEquals("4+5*2", "4+5*2");
		assertInfixIteratorResultEquals("(4+5)*3", "(4+5)*3");
		assertInfixIteratorResultEquals("4*5+4", "4*5+4");
		assertInfixIteratorResultEquals("4*(5+5)", "4*(5+5)");
		assertInfixIteratorResultEquals("4*5+6", "4*5+6");
		assertInfixIteratorResultEquals("4+5+7", "4+5+7");
		assertInfixIteratorResultEquals("(4+5)*(6+2)", "(4+5)*(6+2)");
		assertInfixIteratorResultEquals("4*(1+3)*(6+2)", "4*(1+3)*(6+2)");
		// notice, the iterator does simplify parenthesis.
		assertInfixIteratorResultEquals("(4+5)+7", "4+5+7");
		assertInfixIteratorResultEquals("(4+5)*(7*(4*(5+7)))", 
			             "(4+5)*7*4*(5+7)");
		assertInfixIteratorResultEquals("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");		
		assertInfixIteratorResultEquals("(4+5)*(7*(4*(5+7 * (8 *( 12 + 4)))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");		
		assertInfixIteratorResultEquals("2","2");
		assertInfixIteratorResultEquals("-2","-2");

	}
	
	@Test
	public void testPreOrderIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.

		assertPreOrderIteratorResult("2","2");
		assertPreOrderIteratorResult("-2","-2");
		assertPreOrderIteratorResult("3+-2", "+3-2");
		assertPreOrderIteratorResult("(7+3)*(-2)", "*+73-2");
		assertPreOrderIteratorResult("(7+3)*(-2)*76.42", "**+73-276.42");
		
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
	}
	
	@Test
	public void testPrefixIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.
		assertPrefixIteratorResultEquals("4+1", "+41");
		assertPrefixIteratorResultEquals("4+5*2", "+4*52");
		assertPrefixIteratorResultEquals("(4+5)*3", "*+453");
		assertPrefixIteratorResultEquals("4*5+4", "+*454");
		assertPrefixIteratorResultEquals("4*(5+5)", "*4+55");
		assertPrefixIteratorResultEquals("4*5+6", "+*456");
		// no difference in next two because infix interpreter
		assertPrefixIteratorResultEquals("4+5+7", "++457");
		assertPrefixIteratorResultEquals("(4+5)+7", "++457");
		// but the interpreter does create a diff tree as below.
		assertPrefixIteratorResultEquals("4+(5+7)", "+4+57");
		assertPrefixIteratorResultEquals("(4+5)*(6+2)", "*+45+62");
		assertPrefixIteratorResultEquals("4*(1+3)*(6+2)", "**4+13+62");

		assertPrefixIteratorResultEquals("(4+5)*(7*(4*(5+7)))", 
			            	    "*+45*7*4+57");
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPrefixIteratorResultEquals("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "*+45*7*4+5**78+124");
		assertPrefixIteratorResultEquals("(4+5)*(7*(4*(5+7 *( 8 *( 12 + 4)))))", 
			             "*+45*7*4+5*7*8+124");		
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPrefixIteratorResultEquals("(5+7 * 8 *( 12 + 4))", 
			             "+5**78+124");		
		assertPrefixIteratorResultEquals("(5+7 *( 8 *( 12 + 4)))", 
			             "+5*7*8+124");
		assertPrefixIteratorResultEquals("2","2");
		assertPrefixIteratorResultEquals("-2","-02");
		assertPrefixIteratorResultEquals("3+-2", "+3-02");
		assertPrefixIteratorResultEquals("-5+-6","+-05-06");
		
	}
	
	@Test
	public void testPostOrderIterator(){
		// sigh....
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.
		assertPostOrderIteratorResult("-2","2-");
		assertPostOrderIteratorResult("5+-6","6-5+");
		
		assertPostOrderIteratorResult("4+1", "14+");
		assertPostOrderIteratorResult("4+5*2", "25*4+");
		assertPostOrderIteratorResult("(4+5)*3", "354+*");
		assertPostOrderIteratorResult("4*5+4", "454*+");
		assertPostOrderIteratorResult("4*(5+5)", "55+4*");
		assertPostOrderIteratorResult("4*5+6", "654*+");
		// no difference in next two because infix interpreter
		assertPostOrderIteratorResult("4+5+7", "754++");
		assertPostOrderIteratorResult("(4+5)+8", "854++");
		// but the interpreter does create a diff tree as below.
		assertPostOrderIteratorResult("4+(5+9)", "95+4+");
		assertPostOrderIteratorResult("(4+5)*(6+2)", "26+54+*");
		assertPostOrderIteratorResult("4*(1+3)*(6+2)", "26+31+4**");

		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7)))", 
			            	      "75+4*7*54+*");
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             	      "412+87**5+4*7*54+*");
		assertPostOrderIteratorResult("(4+5)*(7*(4*(5+7 *( 8 *( 12 + 4)))))", 
			                      "412+8*7*5+4*7*54+*");		
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostOrderIteratorResult("(5+7 * 8 *( 12 + 4))", 
			             	      "412+87**5+");		
		assertPostOrderIteratorResult("(5+7 *( 8 *( 12 + 4)))", 
			             	      "412+8*7*5+");
		assertPostOrderIteratorResult("2","2");
		

	}
	
	
	@Test
	public void testPostFixIterator(){
		// The first parameter is to be interpreted by an InfixInterpreter
		// which creates the expression tree to iterate through. The second 
		// result is the output from the iteration in pre order. Note that
		// the user of the iterator is resposible for spacing symbols.
		assertPostFixIteratorResult("4+1", "41+");
		assertPostFixIteratorResult("4+5*2", "452*+");
		assertPostFixIteratorResult("(4+5)*3", "45+3*");
		assertPostFixIteratorResult("4*5+4", "45*4+");
		assertPostFixIteratorResult("4*(5+5)", "455+*");
		assertPostFixIteratorResult("4*5+6", "45*6+");
		// no difference in next two because infix interpreter
		assertPostFixIteratorResult("4+5+7", "45+7+");
		assertPostFixIteratorResult("(4+5)+8", "45+8+");
		// but the interpreter does create a diff tree as below.
		assertPostFixIteratorResult("4+(5+9)", "459++");
		assertPostFixIteratorResult("(4+5)*(6+2)", "45+62+*");
		assertPostFixIteratorResult("4*(1+3)*(6+2)", "413+*62+*");

		assertPostFixIteratorResult("(4+5)*(7*(4*(5+7)))", 
			            	      "45+7457+***");
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostFixIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             	      "45+74578*124+*+***");
		assertPostFixIteratorResult("(4+5)*(7*(4*(5+7 *( 8 *( 12 + 4)))))", 
			                      "45+74578124+**+***");		
		// the follwing pair of lines each evaluate differently
		// because infix interpreter created the expression tree.
		assertPostFixIteratorResult("(5+7 * 8 *( 12 + 4))", 
			             	      "578*124+*+");		
		assertPostFixIteratorResult("(5+7 *( 8 *( 12 + 4)))", 
			             	      "578124+**+");
		assertPostFixIteratorResult("2","2");
		
		
		assertPostFixIteratorResult("-2","02-");
		assertPostFixIteratorResult("5+-6","506-+");
		assertPostFixIteratorResult("-5+-6","05-06-+");
		

	}	

	
	private void assertInOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expressionTree
		Iterator<ComponentNode> iterator = new InOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private void assertInfixIteratorResultEquals(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expressionTree
		Iterator<ComponentNode> iterator = new InFixIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private void assertPreOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PreOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private void assertPrefixIteratorResultEquals(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PreFixIterator(expTree);
		assertIteratorEquals(output, iterator);
	}

	private void assertPostOrderIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PostOrderIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	private void assertPostFixIteratorResult(String input, String output){
		// create an infix expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the expression tree
		Iterator<ComponentNode> iterator = new PostFixIterator(expTree);
		assertIteratorEquals(output, iterator);
	}
	
	
	private ExpressionTree getExpressionTree(String input){
		// create an expression tree from input string.
		ExpressionTreeContext context = new ExpressionTreeContext();
		context.setFormat("infix");
		Interpreter interpreter = new InfixInterpreter();
		ExpressionTree expTree = null;
		try {
			expTree = interpreter.interpret(context, input);
			assertNotNull("Unable to get expression tree from input "
				+ input + ".", expTree);		
			
		} catch (Exception e){
			System.out.println(e.toString());
		}
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
