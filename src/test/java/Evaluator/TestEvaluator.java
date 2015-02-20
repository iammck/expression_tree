import org.junit.*;
import static org.junit.Assert.*;

public class TestEvaluator {
	
	@Test
	public void testCreateEvaluator(){
		Evaluator evaluator;
		evaluator = new InfixEvaluator();
		evaluator = new PrefixEvaluator();
		evaluator = new PostfixEvaluator();
	}
	
	@Test
	public void testInfixEvaluatorEvaluate(){
		Evaluator evaluator = new InfixEvaluator();
		testEvaluatorEvaluate(evaluator, "infix");
	}
	
	@Test
	public void testPrefixEvaluatorEvaluate(){
		Evaluator evaluator = new PrefixEvaluator();
		testEvaluatorEvaluate(evaluator, "prefix");
	}
	
	@Test
	public void testPostfixEvaluatorEvaluate(){
		Evaluator evaluator = new PostfixEvaluator();
		testEvaluatorEvaluate(evaluator, "postfix");
	}
	
	private void testEvaluatorEvaluate(Evaluator evaluator, String treeOrder){
		// get a new context. 
		ExpressionTreeContext context = new ExpressionTreeContext();
		// the context must have a tree order for the iterator.
		context.setTreeOrder(treeOrder);
		// This evaluator should throw a null pointer exception with null tree.
		ExpressionTree expressionTree = null;
		assertCatchExpressionTreeExceptionFromEvaluate(context, evaluator, expressionTree); 
		// Assert that the answer is obtained.
		expressionTree = getExpressionTree(context, "2+3");
		assertNotNull("expressionTree should not be null!", expressionTree);
		double result = -42;
		try{
			ExpressionTree expTree = 
					evaluator.evaluate(context, expressionTree);
			result = Double.valueOf(expTree.getRoot().getItem());
			
		} catch (Exception e ){
			e.toString();
		}
		assertEquals("The answer should be 5, but is "
				+ result + ".", 5, result, 0.01 );
		// Assert that the answer is obtained.
		expressionTree = getExpressionTree(context, "(2+3)");
		try{
			ExpressionTree expTree = 
					evaluator.evaluate(context, expressionTree);
			result = Double.valueOf(expTree.getRoot().getItem());
		} catch (Exception e ){
			e.toString();
		}
		assertEquals("The answer should be 5, but is "
				+ result + ".", 5, result, 0.01 );
		// Assert that the answer is obtained.
		expressionTree = getExpressionTree(context, "(2+3)*(4-5)*(-1)");
		try{
			ExpressionTree expTree = 
					evaluator.evaluate(context, expressionTree);
			result = Double.valueOf(expTree.getRoot().getItem());
		} catch (Exception e ){
			e.toString();
		}
		assertEquals("The second answer should be 5, but is "
				+ result + ".", 5, result, 0.01 );
	
	}
	
	private void testEvaluatorFail(Evaluator evaluator, String treeOrder){
		// get a new context. 
		ExpressionTreeContext context = new ExpressionTreeContext();
		// the context must have a tree order for the iterator.
		context.setTreeOrder(treeOrder);
		// This evaluator should throw a null pointer exception with null tree.
		ExpressionTree expressionTree = null;
		// Assert that an expcetion is thrown.
		expressionTree = getExpressionTree(context, "(2+3)*(4-5)*(-1)");
		testThrowsInvalidInputException(context, evaluator, expressionTree);
	}
	
	private void testThrowsInvalidInputException( ExpressionTreeContext context,
				Evaluator evaluator, ExpressionTree expressionTree){
		try{			
			evaluator.evaluate(context, expressionTree);
			fail("should have thrown an error.");
		} catch (InvalidInputException e){
			return;
		} catch (Exception e){
			fail("thre wrong error for this test.");
		}
	}
	
	private ExpressionTree getExpressionTree(ExpressionTreeContext context, String input){
		ExpressionTree result = null;
		Interpreter interp = new InfixInterpreter();
		try {
			return interp.interpret(context, input);		
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return null;
	}
	
	
	private void assertCatchExpressionTreeExceptionFromEvaluate(
			ExpressionTreeContext context,
			Evaluator evaluator, ExpressionTree expressionTree){
	
		try {
			
			evaluator.evaluate(context, expressionTree);
			fail("Should have caught exception.");
		} catch (ExpressionTreeException e){
			return;
		} catch (Exception e){
			fail("Caught some other exception.");
		}
		
	}

}
