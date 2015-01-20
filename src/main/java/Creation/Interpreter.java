import java.util.*;

public class Interpreter {
	public ExpressionTree interpret(InterpreterContext context, String input)
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
			return null;
		} else if (interpretedSymbols.size() == 1) {
		// build the expression tree from interpred symbols and return it.
			rootNode = interpretedSymbols.get(
				interpretedSymbols.size()-1).build();
				return new ExpressionTree(rootNode);
		} else {
			throw new InvalidInputException(
				"InpterpretSymbols has unhandled terms.",
				null, interpretedSymbols, accumSymbols);
		}
	}
	
	public List<String> getInputList(String input){
		// Itterate through the list one
		List<String> result = new ArrayList<String>();
		int inputLength = input.length();
		int index = 0; 
		while (index < inputLength){
			// Get next symbol
			index = getNextSymbol(index, input, result);
		}		
		return result;
	}
	
	private int getNextSymbol(int index, String input, List<String> output){
		// The length of the input
		int inputLength = input.length();
		// char for the current character
		char current;
		// remove any white space
		while (index < inputLength && isWhiteSpace(input.charAt(index))){
			index +=1;
		}
		// if the index is out of bounds, return.
		if (index >= inputLength){
			return index;
		}
		current = input.charAt(index);
		// if ch is a number or a period, get and return the next number.
		if ( ((int) current >=48 && (int) current <= 57) 
						|| (int) current == 46){
			return getNextNumber(index, input, output);
		} // Otherwise use a while loop to build up to a symbol
		StringBuilder result = new StringBuilder();
		while (index < inputLength){
			result.append(input.charAt(index));
			// is the result a valid symbol
			if (isValidSymbol(result.toString())){
				output.add(result.toString());
				return index + 1;
			}
			index += 1;
		}
		return index;
	}
	
	private boolean isWhiteSpace(char ch){
		if (ch <= 32 || ch >= 127)
			return true;
		return false;
	}
	
	private boolean isValidSymbol(String symbol){
		// Atm, all symbols are a single char.
		return true;
	}
	
	private int getNextNumber(int index, String input, List<String> output){
		StringBuilder result = new StringBuilder();
		int inputLength = input.length();
		while(index < inputLength){
			char ch = input.charAt(index);
			if (((int) ch >=48 && (int) ch <= 57)){
				result.append(ch);
			} else if ((int) ch == 46) {
				result.append(ch);
			} else {
				break;
			}
			index += 1;
		}
		if (result.length() > 0){
			output.add(result.toString());
		}
		return index;
	}

	private boolean isNumber(String item){
		try{
			Float.valueOf(item);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	private boolean isBinaryOperator(String item){
		if(item.equals("+") || item.equals("-") 
			|| item.equals("*") || item.equals("/")){
			return true;
		}
		return false;
	}
	
	private boolean isUnaryOperator(String item, String prevItem){
		if (item.equals("-") && 
			(prevItem == null || (!isNumber(prevItem)) ) ){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isParenthesis(String item){
		if (item.equals("(") || item.equals(")")){
			return true;
		} else {
			return false;
		}
	}	
}
