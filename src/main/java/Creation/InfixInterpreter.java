import java.util.*;
import java.util.Collections;

public class InfixInterpreter extends Interpreter {
public ExpressionTree interpret(ExpressionTreeContext context, String input)
			throws InvalidInputException{
		// List of accumulated symbols waiting to be put in parsed list.
		List<Symbol> accumSymbols = new ArrayList<Symbol>();
		List<Symbol> interpretedSymbols = new ArrayList<Symbol>();
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
						"must have an extra (",
						null, interpretedSymbols,
						accumSymbols);
				}
				result = new Parenthesis(item);
				
			}
			if (result != null)
				result.addToSymbols(interpretedSymbols, accumSymbols);
			else
				throw new InvalidInputException(item + " is invalid input.",
					null, interpretedSymbols, accumSymbols);
			prevItem = item;
		}
		// If accumOperators greater than 0, attempt interpret.
		while (accumSymbols.size() > 0){
			Symbol op = accumSymbols.get(accumSymbols.size()-1);
			if(op.interpret(interpretedSymbols)){
				accumSymbols.remove(accumSymbols.size()-1);
			} else {
				String result = "Unable to interpret symbol "
					+ op.toString()
					+ " in accumSymbols list.\n"
					+ "interpretedSymbols: " 
					+ interpretedSymbols.toString()
					+ "\naccumSymbols: " 
					+ accumSymbols.toString();
					
				throw new InvalidInputException(result, 
					op, interpretedSymbols, accumSymbols);
			}
		}
		ComponentNode rootNode = null;
		if (interpretedSymbols.size() == 0 ){
			String result = "Empty input String.";					
			throw new InvalidInputException(result, null, null, null);
		} else if (interpretedSymbols.size() == 1) {
		// build the expression tree from interpred symbols and return it.
			rootNode = interpretedSymbols.get(
				interpretedSymbols.size()-1).build();
				return new ExpressionTree( context, rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretSymbols has unhandled terms.",
				null, interpretedSymbols, accumSymbols);
		}
	}
}
