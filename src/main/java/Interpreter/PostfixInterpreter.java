import java.util.*;
import java.util.Collections;

public class PostfixInterpreter extends Interpreter {

	public ExpressionTree interpret(ExpressionTreeContext context, String input)
			throws InvalidInputException{
		List<Symbol> interpretedSymbols = new ArrayList<Symbol>();
		List<String> inputList = getInputList(input);
		for(String item: inputList){
			//if the item is a number
			if (isNumber(item)){
				Number number;
				number = new Number(item);
				number.interpret(interpretedSymbols);
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
				} else {
					throw new InvalidInputException(
						item + " is invalid input.");
				}
				if (!operator.interpret(interpretedSymbols)){
					throw new InvalidInputException(
						item + " was not interpreted.");
				}
			} else {
				throw new InvalidInputException(
					item + " is invalid input.");
			}
		}
		
		ComponentNode rootNode = null;
		if (interpretedSymbols.size() == 0 ){
			throw new InvalidInputException("Empty input String.");
		} else if (interpretedSymbols.size() == 1) {
		// build the expression tree from interpred symbols and return it.
			rootNode = interpretedSymbols.get(
				interpretedSymbols.size()-1).build();
				return new ExpressionTree( context, rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretSymbols has unhandled terms.");
		}
	}
}
