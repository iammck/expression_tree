import java.util.*;
import java.util.Collections;

public class InfixInterpreter extends Interpreter {
public ExpressionTree interpret(ExpressionTreeContext context, String input)
			throws InvalidInputException{
		
		// must check for null input here, but maybe make call to super to do it?
		// bcause having to do it for each interpreter subclass.
		if (input == null)
			throw new InvalidInputException("The input expression must not be non-existent.");					
		// List of accumulated interpretables waiting to be put in parsed list.
		List<Interpretable> pendingList = new ArrayList<Interpretable>();
		List<Interpretable> interpretedList = new ArrayList<Interpretable>();
		List<String> inputList = getInputList(input);
		// Keep track of last interpretable for uniary operators.
		// and the parenthesis cound
		int parenthesisCount = 0;
		String prevItem = null;
		for(String item: inputList){
			Interpretable result = null;
			//if the interpretable is a number
			if (isNumber(item)){
				result = new Number(item);
			// else if item is a unary operator
			} else if (isUnaryOperator(item, prevItem)){
				// only unary is negation.
				result = new Negation();
			// else if interpretable is a binary operator
			} else if (isBinaryOperator(item)){
				// create the right operator
				if (item.equals("+")){
					result = new Addition();
				} else if (item.equals("-")){
					result = new Subtraction();
				} else if (item.equals("*")){
					result = new Multiplication();
				} else if (item.equals("/")){
					result = new Division();
				} else if (item.equals("^")){
					result = new Exponential();
				}
			// else if item is a parenthesis
			} else if ( isParenthesis(item)){
				if (item.equals("(")){
						parenthesisCount++;
				} else if (--parenthesisCount < 0) {
					throw new InvalidInputException(
						"must have an extra (");
				}
				result = new Parenthesis(item);
				
			}
			// if obtained a result 
			if (result != null)
				result.addToInterpreter(interpretedList, pendingList);
			else
				throw new InvalidInputException(
					item + " is invalid input.");
			prevItem = item;
		}
		// If pendingList greater than 0, attempt interpret.
		while (pendingList.size() > 0){
			Interpretable lastInterpretable = pendingList.get(pendingList.size()-1);
			if(lastInterpretable.interpret(interpretedList)){
				pendingList.remove(lastInterpretable);
			} else {
				throw new InvalidInputException(
					"Unable to interpret interpretable "
					+ lastInterpretable.toString());
			}
		}
		ComponentNode rootNode = null;
		if (interpretedList.size() == 0 ){
			throw new InvalidInputException(
				"Empty input String.");
		} else if (interpretedList.size() == 1) {
		// build the expression tree from interpred interpretables and return it.
			rootNode = interpretedList.get(
				interpretedList.size()-1).build();
				ExpressionTree.TraversalOrder order = 
						ExpressionTree.TraversalOrder.infix;
				return new ExpressionTree( order, rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretedList has unhandled terms: " +  interpretedList);
		}
	}
}
