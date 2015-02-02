import java.util.*;
import java.util.Collections;

public class InfixInterpreter extends Interpreter {
public ExpressionTree interpret(ExpressionTreeContext context, String input)
			throws InvalidInputException{
					
		// List of accumulated symbols waiting to be put in parsed list.
		List<Symbol> pendingList = new ArrayList<Symbol>();
		List<Symbol> interpretedList = new ArrayList<Symbol>();
		List<String> inputList = getInputList(input);
		// Keep track of last symbol for uniary operators.
		// and the parenthesis cound
		int parenthesisCount = 0;
		String prevItem = null;
		for(String item: inputList){
			Symbol result = null;
			//if the symbol is a number
			if (isNumber(item)){
				result = new Number(item);
			// else if item is a unary operator
			} else if (isUnaryOperator(item, prevItem)){
				// only unary is negation.
				result = new Negation();
			// else if symbol is a binary operator
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
			if (result != null)
				result.addToInterpreter(interpretedList, pendingList);
			else
				throw new InvalidInputException(
					item + " is invalid input.");
			prevItem = item;
		}
		// If pendingList greater than 0, attempt interpret.
		while (pendingList.size() > 0){
			Symbol lastSymbol = pendingList.get(pendingList.size()-1);
			if(lastSymbol.interpret(interpretedList)){
				pendingList.remove(lastSymbol);
			} else {
				throw new InvalidInputException(
					"Unable to interpret symbol "
					+ lastSymbol.toString());
			}
		}
		ComponentNode rootNode = null;
		if (interpretedList.size() == 0 ){
			throw new InvalidInputException(
				"Empty input String.");
		} else if (interpretedList.size() == 1) {
		// build the expression tree from interpred symbols and return it.
			rootNode = interpretedList.get(
				interpretedList.size()-1).build();
				return new ExpressionTree( context, rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretSymbols has unhandled terms." );
		}
	}
}
