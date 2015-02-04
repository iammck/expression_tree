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
		
		ExpressionTreeContext context = new ExpressionTreeContext();
		Evaluator evaluator;
		evaluator = new InfixEvaluator();
		
		// This evaluator should throw a null pointer exception with null tree.
		ExpressionTree expressionTree = null;
		assertCatchNullPointerExceptionFromEvaluate(evaluator, expressionTree); 
		// Assert that the anser is obtained.
		expressionTree = getExpressionTree(context, "2+3");
		assertNotNull("expressionTree should not be null!", expressionTree);
		float result = evaluator.evaluate(expressionTree);
		assertEquals("The answer should be 5, but is "
				+ result + ".", 5, result, 0.01 );
		
		fail("Needs to be implemented.");
	}
	
	@Test
	public void testPrefixEvaluatorEvaluate(){
		fail("Needs to be implemented.");
	}
	
	@Test
	public void testPostfixEvaluatorEvaluate(){
		fail("Needs to be implemented.");
	}
	
	private ExpressionTree getExpressionTree(ExpressionTreeContext context, String input){
		ExpressionTree result = null;
		Interpreter interp = new InfixInterpreter();		
		return interp.interpret(context, input);		
	}
	
	
	private void assertCatchNullPointerExceptionFromEvaluate(
			Evaluator evaluator, ExpressionTree expressionTree){
	
		try {
			evaluator.evaluate(expressionTree);
			fail("Should have caught exception.");
		} catch (NullPointerException e){
			return;
		}
	}

}
