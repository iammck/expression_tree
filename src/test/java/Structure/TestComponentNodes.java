import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestComponentNodes {
	
	// number leaf nodes used to create composite nodes.
	ComponentNode two, three, four, leftParenthesis, rightParenthesis;
	
	// composite binary and unary nodes.
	ComponentNode multiplication, division, 
			addition, subtraction, negation;
	
	/**
	 * Knowing that number leaf nodes can be created,
	 * creates some leaf nodes to use with objects.
	 *
	 * Knowing that composite binary and unary nodes
	 * can be created, creates several.
	 * 
	 */
	@Before
	public void beforeEachTest(){
		two = new NumberLeafNode(String.valueOf(2));	
		three = new NumberLeafNode(String.valueOf(3));	
		four = new NumberLeafNode(String.valueOf(4));	
		leftParenthesis = new LeftParenthesisLeafNode();
		rightParenthesis = new RightParenthesisLeafNode();

		multiplication = new MultiplicationCompositeBinaryNode(three, four);
		division = new DivisionCompositeBinaryNode(three, four);
		addition  = new AdditionCompositeBinaryNode(three, four);
		subtraction  = new SubtractionCompositeBinaryNode(three, four);
		negation = new NegationCompositeUnaryNode(two);
		
	}
	
	/**
	 *
	 * Test the creation of each leaf node. This test
	 * tests number and parenthesis creation using the composite
	 * base class to check each item for the right type.
	 */
	@Test
	public void testCreateLeafNode(){
		ComponentNode number = new NumberLeafNode(String.valueOf(33));
		checkComponentNodeItem(number,String.valueOf(33));
		checkLeafNodeForChildren(number);
		ComponentNode rightParenthesis = new RightParenthesisLeafNode();
		checkComponentNodeItem(rightParenthesis,String.valueOf(")"));
		checkLeafNodeForChildren(rightParenthesis);
		ComponentNode leftParenthesis = new LeftParenthesisLeafNode();
		checkComponentNodeItem(leftParenthesis,String.valueOf("("));
		checkLeafNodeForChildren(leftParenthesis);
	}
	
	/**
	 *
	 * Test the creation of each composite binary node. This test
	 * tests Multipication, Division, Addition,  Subtraction
	 * creation using the composite base class to check each 
	 * item for the right type.
	 */
	@Test
	public void testCreateCompositeBinaryNode(){
		ComponentNode node;
		
		// Check the multiplication node
		node =	new MultiplicationCompositeBinaryNode(three, four);
		checkComponentNodeItem(node, "*" );
		checkBinaryNodeForChildren(node, three.getItem(), four.getItem());
		// create a second composite binary node
		ComponentNode node2 =	new MultiplicationCompositeBinaryNode(three, four);
		// create a composite unary node
		ComponentNode node3 =	new NegationCompositeUnaryNode(two);
		// check that multiplication node may parent another composite node.
		node =	new MultiplicationCompositeBinaryNode(node2, two);
		checkBinaryNodeForChildren(node, node2.getItem(), two.getItem());
		node =	new MultiplicationCompositeBinaryNode(two, node2);
		checkBinaryNodeForChildren(node, two.getItem(), node2.getItem());
		// check that multiplication node may parent unary node.
		node =	new MultiplicationCompositeBinaryNode(node3, two);
		checkBinaryNodeForChildren(node, node3.getItem(), two.getItem());
		node =	new MultiplicationCompositeBinaryNode(two, node3);
		checkBinaryNodeForChildren(node, two.getItem(), node3.getItem());
		
		// check the division node
		node =	new DivisionCompositeBinaryNode(three, four);
		checkComponentNodeItem(node, "/" );
		checkBinaryNodeForChildren(node, three.getItem(), four.getItem());
		// check that division node may parent another composite node.
		node =	new DivisionCompositeBinaryNode(node2, two);
		checkBinaryNodeForChildren(node, node2.getItem(), two.getItem());
		node =	new DivisionCompositeBinaryNode(two, node2);
		checkBinaryNodeForChildren(node, two.getItem(), node2.getItem());
		// check that division node may parent unary node.
		node =	new DivisionCompositeBinaryNode(node3, two);
		checkBinaryNodeForChildren(node, node3.getItem(), two.getItem());
		node =	new DivisionCompositeBinaryNode(two, node3);
		checkBinaryNodeForChildren(node, two.getItem(), node3.getItem());
		
		node =	new AdditionCompositeBinaryNode(three, four);
		checkComponentNodeItem(node, "+" );
		checkBinaryNodeForChildren(node, three.getItem(), four.getItem());
		// check that addition node may parent another composite node.
		node =	new AdditionCompositeBinaryNode(node2, two);
		checkBinaryNodeForChildren(node, node2.getItem(), two.getItem());
		node =	new AdditionCompositeBinaryNode(two, node2);
		checkBinaryNodeForChildren(node, two.getItem(), node2.getItem());
		// check that addition node may parent unary node.
		node =	new AdditionCompositeBinaryNode(node3, two);
		checkBinaryNodeForChildren(node, node3.getItem(), two.getItem());
		node =	new AdditionCompositeBinaryNode(two, node3);
		checkBinaryNodeForChildren(node, two.getItem(), node3.getItem());
		
		node =	new SubtractionCompositeBinaryNode(three, four);
		checkComponentNodeItem(node, "-" );
		checkBinaryNodeForChildren(node, three.getItem(), four.getItem());
		// check that subtraction node may parent another composite node.
		node =	new SubtractionCompositeBinaryNode(node2, two);
		checkBinaryNodeForChildren(node, node2.getItem(), two.getItem());
		node =	new SubtractionCompositeBinaryNode(two, node2);
		checkBinaryNodeForChildren(node, two.getItem(), node2.getItem());
		// check that subtraction node may parent unary node.
		node =	new SubtractionCompositeBinaryNode(node3, two);
		checkBinaryNodeForChildren(node, node3.getItem(), two.getItem());
		node =	new SubtractionCompositeBinaryNode(two, node3);
		checkBinaryNodeForChildren(node, two.getItem(), node3.getItem());
		
	}

	/**
	 *
	 * Test the creation of each composite unary node. This test
	 * tests Negation composite unary node creation. Using the
	 * composite base class the node is checked for the right
	 * state.
	 */
	@Test
	public void testCreateCompositeUnaryNode(){
		ComponentNode node =	
			new NegationCompositeUnaryNode(two);
		checkComponentNodeItem(node, "-" );
		checkUnaryNodeForChildren(node, two.getItem());
		
		// create a composite binary node
		ComponentNode node2 =	new MultiplicationCompositeBinaryNode(three, four);
		// check that negation node may parent another composite node.
		node =	new NegationCompositeUnaryNode(node2);
		checkUnaryNodeForChildren(node, node2.getItem());
		// create another composite unary node
		ComponentNode node3 =	new NegationCompositeUnaryNode(two);
		// check that negation node may parent another composite unary node.
		node =	new NegationCompositeUnaryNode(node3);
		checkUnaryNodeForChildren(node, node3.getItem());
		
	}
		
	/*
	 * Checks the Binary node for child elements. Checks that children
	 * have the same items.
	 */
	private void checkBinaryNodeForChildren(ComponentNode node, 
			String leftChildItem, String rightChildItem){
		assertNotNull(node.getLeftChild());
		assertNotNull(node.getRightChild());
		assertEquals(" BinaryNode " + node.getItem()
				+ " should have left child with item" 
				+ leftChildItem + " but does not.",
				node.getLeftChild().getItem(), leftChildItem);
		assertEquals(" BinaryNode " + node.getItem()
				+ " should have right child with item" 
				+ rightChildItem + " but does not.",
				node.getRightChild().getItem(), rightChildItem);
	}
	
	/*
	 * Checks the Unary node for right child element and null left
	 * child element. Checks that the node child has the same item.
	 */
	private void checkUnaryNodeForChildren(ComponentNode node, 
			String rightChildItem ){
		assertNull(" Left child should be null.", 
			node.getLeftChild());
		assertNotNull(" Right child should note be null.",
			node.getRightChild());
		assertEquals(" UnaryNode " + node.getItem()
				+ " should have right child with item" 
				+ rightChildItem + " but does not.",
				node.getRightChild().getItem(),	rightChildItem);
	}
	
	/*
	 * Checks the leaf node for child element
	 */
	private void checkLeafNodeForChildren(ComponentNode node){
		assertNull(node.getLeftChild());
		assertNull(node.getRightChild());
	}
	
	/*
	 * Checks the Component node for child elements
	 */
	private void checkComponentNodeItem(ComponentNode node, String expected ){
		assertEquals(" The node.getItem() result is not equal to" 
			+ " the expected value of " + expected + ".",
			expected, node.getItem());
	}


	/**
	 *
	 * Test the comparison of two nodes.
	 */
	@Test
	public void testCompareToNode(){
		ComponentNode node;
		ArrayList<ComponentNode> greaterList = new ArrayList<ComponentNode>();
		ArrayList<ComponentNode> equalList = new ArrayList<ComponentNode>();
		ArrayList<ComponentNode> lesserList = new ArrayList<ComponentNode>();
	
		// check number is less than all other nodes.
		node = two; // using number two.
		// number are less then all else, but numbers
		greaterList.add(addition);
		greaterList.add(subtraction);
		greaterList.add(multiplication);
		greaterList.add(division);
		greaterList.add(negation);
		greaterList.add(leftParenthesis);
		greaterList.add(rightParenthesis);
		// numbers are equal to numbers.
		equalList.add(three);
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		
		// check addition and subtraction.
		node = addition; // first addition
		// they are less then all else, but numbers and themselves
		greaterList.remove(addition);
		greaterList.remove(subtraction);
		// they are equal to themselves.
		equalList.remove(three);
		equalList.add(addition);
		equalList.add(subtraction);
		// the are greater than numbers.
		lesserList.add(three);		
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		node = subtraction;
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		
		// check multiplication and division.
		node = multiplication; // first multiplication
		// they are less then negation and parenthesis
		greaterList.remove(multiplication);
		greaterList.remove(division);
		// they are equal to themselves.
		equalList.remove(addition);
		equalList.remove(subtraction);
		equalList.add(multiplication);
		equalList.add(division);
		// the are greater than numbers, addition, and subtraction.
		lesserList.add(three);		
		lesserList.add(addition);
		lesserList.add(subtraction);
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		node = division;
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		
		// check negation.
		node = negation;
		// it is less than and parenthesis
		greaterList.remove(negation);
		// they are equal to themselves.
		equalList.remove(multiplication);
		equalList.remove(division);
		equalList.add(negation);
		// the are greater than all but themself and parenthesis.
		lesserList.add(multiplication);
		lesserList.add(division);
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		
		// check parenthesis.
		node = leftParenthesis; // first left parenthesis
		// they are less then nothing
		greaterList.remove(leftParenthesis);
		greaterList.remove(rightParenthesis);
		// confirm empty greaterList
		assertTrue("greaterList should be empty.",greaterList.isEmpty());
		// they are equal to themselves.
		equalList.remove(negation);
		equalList.add(rightParenthesis);
		equalList.add(leftParenthesis);
		// the are greater then everything else.
		lesserList.add(negation);
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);
		node = rightParenthesis;
		checkCompareToNodeResultIsNegativeOne(node, greaterList);
		checkCompareToNodeResultIsZero(node, equalList);
		checkCompareToNodeResultIsPositiveOne(node, lesserList);		
	}
	
	private void checkCompareToNodeResultIsNegativeOne(
		ComponentNode node, ArrayList<ComponentNode> greaterList){		
		
		for(ComponentNode node2: greaterList){
			if ( node.compareToNode(node2) != -1 ){
				fail(" Fail, node " + node.getItem() 
					+ " compared to node " + node2.getItem()
					+ " is not -1.");
			}
		}
	}
	
	private void checkCompareToNodeResultIsZero(
		ComponentNode node, ArrayList<ComponentNode> equalList){
		
		for(ComponentNode node2: equalList){
			if ( node.compareToNode(node2) != 0 ){
				fail(" Fail, node " + node.getItem() 
					+ " compared to node " + node2.getItem()
					+ " is not 0.");
			}
		}
	}
	
	private void checkCompareToNodeResultIsPositiveOne(
		ComponentNode node, ArrayList<ComponentNode> lesserList){
		for(ComponentNode node2: lesserList){
			if ( node.compareToNode(node2) != +1 ){
				fail(" Fail, node " + node.getItem() 
					+ " compared to node " + node2.getItem()
					+ " is not +1.");
			}
		}
	}
	
	@Test
	public void testExpressionTree(){
		// an expression tree with just a number.
		
		ExpressionTree expTree =  new ExpressionTree(new ExpressionTreeContext(), two);
		ComponentNode result = expTree.getRoot();
		assertEquals("ExpressionTree result root should contain 2",
			"2", result.getItem());
		assertNull("ExpressionTree result should have no children", 
			result.getRightChild());
		assertNull("ExpressionTree result should have no children", 
			result.getLeftChild());
		assertEquals("",
			0, result.compareToNode(three));
		
	}
	
	@Test 
	public void checkExpressionTreeCreationWithBadArgument(){
		try{
			// create with null leaf node is an error
			ExpressionTree result = new ExpressionTree(null, null);
			fail("should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e){
			return;		
		}
	}
}
