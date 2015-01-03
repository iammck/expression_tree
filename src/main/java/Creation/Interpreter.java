import java.util.*;

public class Interpreter {
	public ExpressionTree interpret(InterperterContext context, String input){
		// List of accumulated symbols waiting to be put in parsed list.
		List<Symbol> accumSymbols = new ArrayList<Symbol>();
		List<Symbol> parsedSymbols = new ArrayList<Symbol>();
		// Keep track of last symbol for uniary minus.
		Symbol prevSymbol = null;
		for(String symbol; inputList){
			//if the symbol is a number
			if (isNumber(symbol)){
				Number number = new Number();
				number.setSymbol(number);
				number.addToSymbols(parsedSymbols, accumSymbols);
				prevItem = number;
			// else if item is a unary operator
			} else if (isUnaryOperator(symbol, prevSymbol)){
				UnaryOperator unaryOperator = new Negate();
				unaryOperator.setSymbol(symbol);
				unaryOperator.addToSymbols(parsedSymbols, accumSymbols);
				prevItem = unaryOperator;
			// else if symbol is a binary operator
			} else if (isBinaryOperator(symbol)){
				// create the right operator
				BinaryOperator binaryOperator = null;
				if (symbol.equals("+")){
					binaryOperator = new Add();
				} else if (item.equals("-")){
					binaryOperator = new Subtract();
				} else if (item.equals("*")){
					binaryOperator = new Multiply();
				} else if (item.equals("/")){
					binaryOperator = new Divide();
				}
				binaryOperator.setSymbol(symbol);
				binaryOperator.addToSymbols(parsedSymbols, accumSymbols);
				prevItem = binaryOperator;
			// else if item is a parenthesis
			} else if ( isParenthesis(symbol)){
				Parenthesis parenthesis = new parenthesis();
				parenthesis.setSymbol(symbol);
				parenthesis.addToSymbols(parsedSymbols, accumSymbols);
				prevItem = parenthesis;
			}
		}
		// If accumOperators greater than 1, attempt interpret. Handle time out?
		while (accumSymbols.size() > 1){
			Symbol op = accumSymbols.get(accumSymbols.size()-1);
			op.interprete(parsedSymbols);
		}
		// TODO build the expression tree from parse tree and return it.
		return expressionTree;
	}
	
	private List<String> getInputArray(String input){
		// TODO
		return null;
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
	
	private boolean isUnaryOperator(String prevItem, String item){
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
