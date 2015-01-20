import org.junit.*;

import static org.junit.Assert.*;


public class TestInOrderExpressionTreeIterator {
	
	@Test
	public void testIterator(){
		assertIteratorResult("4+1");
		assertIteratorResult("4+5*2");
		assertIteratorResult("(4+5)*3");
		assertIteratorResult("4*5+4");
		assertIteratorResult("4*(5+5)");
		assertIteratorResult("4*5+6");
		assertIteratorResult("4+5+7");
		assertIteratorResult("(4+5)*(6+2)");
		assertIteratorResult("4*(1+3)*(6+2)");
		// the iterator does simplify parenthesis.
		assertIteratorResult("(4+5)+7", "4+5+7");
		assertIteratorResult("(4+5)*(7*(4*(5+7)))", 
			             "(4+5)*7*4*(5+7)");
		assertIteratorResult("(4+5)*(7*(4*(5+7 * 8 *( 12 + 4))))", 
			             "(4+5)*7*4*(5+7*8*(12+4))");
		
		// create an expression tree from empty input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new Interpreter();
		ExpressionTree expTree = interpreter.interpret(context, "");
		assertNull("There must not exist a resulting "
			+ "expression tree from emtpy input string.",expTree);		
	}
	
	public void assertIteratorResult(String input){
		// create an expression tree from input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new Interpreter();
		ExpressionTree expTree = interpreter.interpret(context, input);
		assertNotNull("There must exists an expression tree.",expTree);		
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
	
	public void assertIteratorResult(String input, String output){
		// create an expression tree from input string.
		InterpreterContext context = new InterpreterContext();
		Interpreter interpreter = new Interpreter();
		ExpressionTree expTree = interpreter.interpret(context, input);
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


}
