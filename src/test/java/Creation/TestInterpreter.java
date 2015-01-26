import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestInterpreter {
	// the interpreter
	Interpreter interpreter;	
	// any InterpreterContext should work
	InterpreterContext context;
	
	@Before
	public void beforeEachTest(){
		// any InterpreterContext should work
		context = new InterpreterContext();
		ExpressionTreeContext ec = new ExpressionTreeContext();
		context.setExpressionTreeContext(ec);
	}	
	
	@Test(timeout=500)
	public void testGetInputList(){
		// will need an interpreter to do the work
		// should work for any subclass
		interpreter = new InfixInterpreter();
		
		// "2",
		String two = "2";
		List<String> result = interpreter.getInputList(two);
		assertEquals("There should be one symbol, 2, in result.",
				1, result.size());
		assertEquals("2 should be the only result.", 
					two, result.get(0));
		
		// "2*", 
		result = interpreter.getInputList("2*");
		assertEquals("There should be two symbols, 2 and * , in result.",
				2, result.size());
		assertEquals("2 should be the first items in result.", 
					"2", result.get(0));
		assertEquals("* should be the second items in result.", 
					"*", result.get(1));
		
		// "2 *",
		result = interpreter.getInputList("2 *");
		assertEquals("There should be two symbols, 2 and * , in result.",
				2, result.size());
		assertEquals("2 should be the first items in result.", 
					"2", result.get(0));
		assertEquals("* should be the second items in result.", 
					"*", result.get(1));
		
		 // "    2    *"
		result = interpreter.getInputList("    2      *");
		assertEquals("There should be two symbols, 2 and * , in result.",
				2, result.size());
		assertEquals("2 should be the first items in result.", 
					"2", result.get(0));
		assertEquals("* should be the second items in result.", 
					"*", result.get(1));
		// check numbers with decimal points, other symbols and parethesis
		// "1.1 + (43 - (.13 - -63) *0.63 / 00.1)" 16 symbols
		// ................
		// ................
		result = interpreter.getInputList("1.1 + (43 - (.13 - -63) *0.63 / 00.1)");
		assertEquals("There should be sixteen items in result.",
				16, result.size());
		assertEquals("1.1 should be the first item in result.", 
					"1.1", result.get(0));
		assertEquals("+ should be the second item in result.", 
					"+", result.get(1));
		assertEquals("( should be the third item in result.", 
					"(", result.get(2));
		assertEquals(") should be the last item in result.", 
					")", result.get(15));
		assertEquals("00.1 should be the second to last item in result.", 
					"00.1", result.get(14));
		assertEquals(".13 should be the seventh item in result.", 
					".13", result.get(6));
		
		
		result = interpreter.getInputList("1.1 43 .13 63 0.63 00.1+-08");
		assertEquals("There should be sixteen items in result.",
				9, result.size());
		assertEquals("1.1 should be the first item in result.", 
					"1.1", result.get(0));
		assertEquals("08 should be the last item in result.", 
					"08", result.get(8));		
	}
	
	private ComponentNode getExpressionTreeRoot(String testString){
		ExpressionTree et = interpreter.interpret(context,testString);
		ComponentNode result = et.getRoot();
		return result;
	}
	
	@Test
	public void testInterperterWithInfix(){
		// set the interpreter
		interpreter = new InfixInterpreter();
		// 3
		String testString = "3";
		ComponentNode result = getExpressionTreeRoot(testString);
		// this should produce an expression tree root node equal to 3 and no child
		assertEquals("ExpressionTree root node should contain 3",
			"3", result.getItem());
		assertNull("ExpressionTree root node should have no children", 
			result.getRightChild());
		assertNull("ExpressionTree root node should have no children", 
			result.getLeftChild());
		// 3 + 4
		testString = "3   +4";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 4 as right child", 
			"4", result.getRightChild().getItem());		
		// (3 + 4)
		testString = "  (3   +  4    )  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 4 as right child", 
			"4", result.getRightChild().getItem());		
		// 3 + 4 * 5
		testString = "  3   +  4* 5  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have * as right child", 
			"*", result.getRightChild().getItem());
		ComponentNode node = result.getRightChild();
		assertEquals("node should have 4 as left child", 
			"4", node.getLeftChild().getItem());
		assertEquals("node should have 5 as right child", 
			"5", node.getRightChild().getItem());
		// (3 + 4) * 5
		testString = "  (  3   +  4)* 5  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain * as root",
			"*", result.getItem());
		assertEquals("ExpressionTree root node should have + as left child", 
			"+", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 5 as right child", 
			"5", result.getRightChild().getItem());
		node = result.getLeftChild();
		assertEquals("node should have 3 as left child", 
			"3", node.getLeftChild().getItem());
		assertEquals("node should have 4 as right child", 
			"4", node.getRightChild().getItem());
		
		// -(3 + 4) * -5		
		testString = "  -(  3   +  4)* - 5  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain * as root",
			"*", result.getItem());
		assertEquals("ExpressionTree root node should have - as left child", 
			"-", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have - as right child", 
			"-", result.getRightChild().getItem());
		// the left child of result. is -
		node = result.getLeftChild();
		// the right child of this node is +
		node = node.getRightChild();
		assertEquals("node should be +",
			"+", node.getItem());
		assertEquals("node should have 3 as left child", 
			"3", node.getLeftChild().getItem());
		assertEquals("node should have 4 as right child", 
			"4", node.getRightChild().getItem());		
		// -( -3 + 4  ) * ----- 5		
		testString = "  -  (  -3   +  4   )* ----- 5  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain * as root",
			"*", result.getItem());
		assertEquals("ExpressionTree root node should have - as left child", 
			"-", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have - as right child", 
			"-", result.getRightChild().getItem());
		// the left child of result. is -
		node = result.getLeftChild();
		assertEquals("node should be -",
			"-", node.getItem());
		// it should have a plus child with - left and 4 right childern
		assertEquals("node right child should be +",
			"+", node.getRightChild().getItem());
		assertEquals("node right child left child should be -",
			"-", node.getRightChild().getLeftChild().getItem());
		assertEquals("node right child right child should be 4",
			"4", node.getRightChild().getRightChild().getItem());
		
		node = result.getRightChild()
			.getRightChild()
			.getRightChild()
			.getRightChild()
			.getRightChild();
		// the next is not a -
		node = node.getRightChild();
		// the item of this node is 5
		assertEquals("node should be 5",
			"5", node.getItem());
		
		// the following two lines should interprete to the same tree
		//("(5+7 * 8 *( 12 + 4))", "+5**78+124");
		//("(5+7 *( 8 *( 12 + 4)))","+5*7*8+124");
		// iterating through in pre order shows that they are not.
		// It happens because the parenthesis change the order of evaluation
		// 7*8 is evaluated first in the first statement and
		// (8*....) is evaluated first in the second when the parenthesis
		// are done with addToSymbols, perhaps, the prev operator can be
		// interpreted? Testing showed no effect.
	}
	
	@Test
	public void testInterperterWithPrefix(){
		// create the interpreter
		interpreter = new PrefixInterpreter();
		// 3
		String testString = "3";
		ComponentNode result = getExpressionTreeRoot(testString);
		// this should produce an expression tree equal to 3 and no child
		assertEquals("ExpressionTree root node should contain 3",
			"3", result.getItem());
		assertNull("ExpressionTree root node should have no children", 
			result.getRightChild());
		assertNull("ExpressionTree root node should have no children", 
			result.getLeftChild());
		
		// (3 + 4)  = + 3 4
		testString = "  + 3 4  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 4 as right child", 
			"4", result.getRightChild().getItem());		
		// 3 + 4 * 5 =   + 3 * 4 5 
		testString = "   + 3 * 4  5     ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have * as right child", 
			"*", result.getRightChild().getItem());
		ComponentNode node = result.getRightChild();
		assertEquals("node should have 4 as left child", 
			"4", node.getLeftChild().getItem());
		assertEquals("node should have 5 as right child", 
			"5", node.getRightChild().getItem());
		
		// (3 + 4) * 5 = *+345
		testString = "  *    	+ 3 4 5  ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain * as root",
			"*", result.getItem());
		assertEquals("ExpressionTree root node should have + as left child", 
			"+", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 5 as right child", 
			"5", result.getRightChild().getItem());
		node = result.getLeftChild();
		assertEquals("node should have 3 as left child", 
			"3", node.getLeftChild().getItem());
		assertEquals("node should have 4 as right child", 
			"4", node.getRightChild().getItem());
	}

	@Test
	public void testInterperterWithPostfix(){
		// create the interpreter
		interpreter = new PostfixInterpreter();
		// 3
		String testString = "3";
		ComponentNode result = getExpressionTreeRoot(testString);
		// this should produce an expression tree equal to 3 and no child
		assertEquals("ExpressionTree root node should contain 3",
			"3", result.getItem());
		assertNull("ExpressionTree root node should have no children", 
			result.getRightChild());
		assertNull("ExpressionTree root node should have no children", 
			result.getLeftChild());
		
		// (3 + 4)  = + 3 4 = 3 4 +
		testString = "  3 4 + ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 4 as right child", 
			"4", result.getRightChild().getItem());		
		// 3 + 4 * 5 =   + 3 * 4 5 = 3 4 5 * + 
		testString = "   3  4  5 * +     ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain + as root",
			"+", result.getItem());
		assertEquals("ExpressionTree root node should have 3 as left child", 
			"3", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have * as right child", 
			"*", result.getRightChild().getItem());
		ComponentNode node = result.getRightChild();
		assertEquals("node should have 4 as left child", 
			"4", node.getLeftChild().getItem());
		assertEquals("node should have 5 as right child", 
			"5", node.getRightChild().getItem());
		
		// (3 + 4) * 5 = *+345 = 3 4 + 5 *
		testString = "  3 4 + 5 * ";
		result = getExpressionTreeRoot(testString);
		assertEquals("ExpressionTree root node should contain * as root",
			"*", result.getItem());
		assertEquals("ExpressionTree root node should have + as left child", 
			"+", result.getLeftChild().getItem());
		assertEquals("ExpressionTree root node should have 5 as right child", 
			"5", result.getRightChild().getItem());
		node = result.getLeftChild();
		assertEquals("node should have 3 as left child", 
			"3", node.getLeftChild().getItem());
		assertEquals("node should have 4 as right child", 
			"4", node.getRightChild().getItem());
	}
}
