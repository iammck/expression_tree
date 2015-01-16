import java.util.*;

public class Interpreter {
	public ExpressionTree interpret(InterpreterContext context, String input){
		// List of accumulated symbols waiting to be put in parsed list.
		List<Symbol> accumSymbols = new ArrayList<Symbol>();
		List<Symbol> parsedSymbols = new ArrayList<Symbol>();
		List<String> inputList = getInputList(input);
		// Keep track of last symbol for uniary operators.
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
				result = new Parenthesis(item);
			}
			result.addToSymbols(parsedSymbols, accumSymbols);

			prevItem = item;
		}
		// If accumOperators greater than 1, attempt interpret. Handle time out?
		while (accumSymbols.size() > 1){
			Symbol op = accumSymbols.get(accumSymbols.size()-1);
			op.interpret(parsedSymbols);
		}
		// build the expression tree from parse tree and return it.
		ComponentNode rootNode = 
				accumSymbols.get(accumSymbols.size()-1).build();
		return new ExpressionTree(rootNode);
	}
	
	private List<String> getInputList(String input){
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
		// remove any while space
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
		while (inputLength < index){
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
		if (item.equals("-") && (prevItem == null || prevItem.equals("(") )){
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
