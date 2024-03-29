import java.util.*;

public class PrefixInterpreter extends Interpreter {

	
	public ExpressionTree interpret(ExpressionTreeContext context, String input)
			throws InvalidInputException{
		
		
		
		// must check for null input here.
		if (input == null)
			throw new InvalidInputException("The input expression must not be non-existent.");
		
		// List of accumulated interpretables waiting to be put in parsed list.
		List<Interpretable> interpretedInterpretables = new ArrayList<Interpretable>();
		List<String> inputList = getInputList(input);
		// working in reverse
		Collections.reverse(inputList);
		for(String item: inputList){
			//if the item is a number
			if (isNumber(item)){
				Number number;
				number = new Number(item);
				number.interpret(interpretedInterpretables);
			// else if the item is a binary operator
			} else if (isBinaryOperator(item)){
				// create the right operator
				BinaryOperator operator = null;
				if (item.equals("+")){
					operator = new Addition();
				} else if (item.equals("-")){
					operator = new Subtraction();
				} else if (item.equals("*")){
					operator = new Multiplication();
				} else if (item.equals("/")){
					operator = new Division();
				} else if (item.equals("^")){
					operator = new Exponential();
				} else {
					throw new InvalidInputException(
						item + " is invalid input.");
				}
				if (!operator.interpret(interpretedInterpretables)){
					throw new InvalidInputException(
						item + " unable to inpterprete." );
				}
				operator.swapLeftRightSymbols();
			} else {
				throw new InvalidInputException(
					item + " is invalid input." );
			}
		}
		
		ComponentNode rootNode = null;
		if (interpretedInterpretables.size() == 0 ){
			throw new InvalidInputException("Empty input String.");
		} else if (interpretedInterpretables.size() == 1) {
		// build the expression tree from interpred interpretables and return it.
			rootNode = interpretedInterpretables
					.get(interpretedInterpretables.size()-1).build();
			ExpressionTree.TraversalOrder order = 
					ExpressionTree.TraversalOrder.prefix;
			return new ExpressionTree( order, rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretInterpretables has unhandled terms.");
		}
	}


}
