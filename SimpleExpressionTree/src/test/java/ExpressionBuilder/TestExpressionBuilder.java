import org.junit.*;
import static org.junit.Assert.*;

public class TestExpressionBuilder {
	
	@Test
	public void testExpressionbuilder(){
		testWithExpTree("2+3", "2 + 3");	
		testWithExpTree("(2+3)", "2 + 3");	
		testWithExpTree("(2+3) * 5", "( 2 + 3 ) * 5");	
		testWithExpTree("-2+-3", "- 2 + - 3");	
	}
	
	private void testWithExpTree(String input, String output){
		ExpressionTree expTree;
		expTree = getExpressionTree(input);
		ExpressionBuilder builder;
		builder = new ExpressionBuilder(expTree);
		String result = builder.build();
		assertEquals( "Given " + input + " as input,"
			+ " the result of " + result
			+ " did not match the expected output of " + output,
			output, result);
	}
	
	private ExpressionTree getExpressionTree(String input){
		// get a context and set the tree order
		ExpressionTreeContext context;
		context = new ExpressionTreeContext();
		// create the interpreter and inpterprete the input
		Interpreter interpreter = new InfixInterpreter();
		try{
			return interpreter.interpret(context, input);
		} catch (Exception e){
			System.out.println(e.toString());
		}
		return null;		
	}
}
