import java.util.*;

public abstract class Interpreter {
	
	public abstract ExpressionTree interpret(
		ExpressionTreeContext context, String input) 
		throws InvalidInputException;
	
	public List<String> getInputList(String input){
		// Itterate through the list one
		List<String> result = new ArrayList<String>();
		int inputLength = input.length();
		int index = 0; 
		while (index < inputLength){
			// Get next interpretable
			index = getNextInterpretable(index, input, result);
		}		
		return result;
	}
	
	private int getNextInterpretable(int index, String input, List<String> output){
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
		} // Otherwise use a while loop to build up to a interpretable
		StringBuilder result = new StringBuilder();
		while (index < inputLength){
			result.append(input.charAt(index));
			// is the result a valid interpretable
			if (isValidInterpretable(result.toString())){
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
	
	private boolean isValidInterpretable(String Interpretable){
		// Atm, all interpretables are a single char.
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

	protected boolean isNumber(String item){
		try{
			Float.valueOf(item);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	protected boolean isBinaryOperator(String item){
		if(item.equals("+") || item.equals("-") || item.equals("^")
			|| item.equals("*") || item.equals("/")){
			return true;
		}
		return false;
	}
	
	protected boolean isUnaryOperator(String item, String prevItem){
		if (item.equals("-") && 
			(prevItem == null || (!isNumber(prevItem)) ) ){
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isParenthesis(String item){
		if (item.equals("(") || item.equals(")")){
			return true;
		} else {
			return false;
		}
	}	
}
