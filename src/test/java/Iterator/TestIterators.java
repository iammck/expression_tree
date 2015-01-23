import org.junit.*;

import static org.junit.Assert.*;


public class TestIterators {
	
	
	
	@Test
	public void testInOrderExpressionTreeIterator(){
		// testing in order iteration, can create expression tree with infix.
		assertInOrderExpressionTreeIteratorIterateResult("4+1");
		assertInOrderExpressionTreeIteratorIterateResult("4+5*2");
		assertInOrderExpressionTreeIteratorIterateResult("(4+5)*3");
		assertInOrderExpressionTreeIteratorIterateResult("4*5+4");
		assertInOrderExpressionTreeIteratorIterateResult("4*(5+5)");
		assertInOrderExpressionTreeIteratorIterateResult("4*5+6");
		assertInOrderExpressionTreeIteratorIterateResult("4+5+7");
		assertInOrderExpressionTreeIteratorIterateResult("(4+5)*(6+2)");
		assertInOrderExpressionTreeIteratorIterateResult("4*(1+3)*(6+2)");
		// the iterator does simplify parenthesis.
		assertInOrderExpressionTreeIteratorIterateResult("(4+5)+7", "4+5+7");
		assertInOrderExpressionTreeIteratorIterateResult("(4+5)*(7*(4*(5+7)))", 
			             "(4+5)*7*4*(5+7)");
		assertInOrderExpressionTreeIteratorIterateResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");
		
		// create an expression tree from empty input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new Interpreter();
		ExpressionTree expTree = interpreter.interpret(context, "");
		assertNull("There must not exist a resulting "
			+ "expression tree from emtpy input string.",expTree);		
	}
	
	@Test
	public void testPreOrderExpressionTreeIterator(){
		fail("method needs to be implemented.");
	}
	
	@Test
	public void testPostOrderExpressionTreeIterator(){
		fail("method needs to be implemented.");
	}
		
	
	
	public void assertInOrderExpressionTreeIteratorIterateResult(String input){
		ExpressionTree expTree = getExpressionTree(input);
		// create an iterator with the input
		InOrderExpressionTreeIterator iterator = new InOrderExpressionTreeIterator(expTree);
		StringBuilder resultBuilder = new StringBuilder();
		while(iterator.hasNext()){
			ComponentNode node = iterator.next();
			resultBuilder.append(node.getItem());	
			
		}
		assertEquals("iterator did not result in " + input 
			+ ", but was " + resultBuilder.toString(),
					input, resultBuilder.toString());
	}
	
	public void assertInOrderExpressionTreeIteratorIterateResult(String input, String output){
		// create an expression tree from input string.
		ExpressionTree expTree = getExpressionTree(input);
		assertNotNull("There must exists an expression tree.",expTree);		
		// create an iterator with the input
		InOrderExpressionTreeIterator iterator = new InOrderExpressionTreeIterator(expTree);
		StringBuilder resultBuilder = new StringBuilder();
		while(iterator.hasNext()){
			ComponentNode node = iterator.next();
			resultBuilder.append(node.getItem());	
			
		}
		assertEquals("iterator did not result in " + output 
			+ ", but was " + resultBuilder.toString(),
					output, resultBuilder.toString());		
	}

	private ExpressionTree getExpressionTree(String input){
		// create an expression tree from input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new Interpreter();
		ExpressionTree expTree = interpreter.interpret(context, input);
		assertNotNull("Unable to get expression tree from input "
			+ input + ".", expTree);		
		return expTree;
	}


}
