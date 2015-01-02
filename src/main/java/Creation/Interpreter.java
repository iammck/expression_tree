import java.util.*;

public class Interpreter {
	public ExpressionTree interpret(InterperterContext context, String input){
		
		List<Operator> accumOperators = new ArrayList<Operator>();
		List<Symbol> parsedList = new ArrayList<Symbol>();

		for(String symbol; inputList){
			Symbol prevItem;
			//if the symbol is a number
			if (isNumber(symbol)){
				Number number = new Number();
				number.setSymbol(number);
				number.interpret(parseTree);
				prevItem = number;
			// else if item is a unary operator
			} else if (isUnaryOperator(item, prevItem)){
				UnaryOperator operator = new Negate();
				operator.addToAccumOperators(accumOperators);
				prevItem = operator;
			// else if symbol is a binary operator
			} else if (isBinaryOperator(symbol)){
				// create the right operator
				BinaryOperator operator = null;
				if (item.equals("+")){
					operator = new Add();
				} else if (item.equals("-")){
					operator = new Subtract();
				} else if (item.equals("*")){
					operator = new Multiply();
				} else if (item.equals("/")){
					operator = new Divide();
				}
				operator.addToAccumOperators(accumOperators);
				prevItem = operator;
			// else if item is a parenthesis
			} else if ( isParenthesis(item)){
				Parenthesis operator = new parenthesis();
				operator.interpret(parseList, accumOperators);
				prevItem = operator;
			}
		}
		// If accumOperators greater than 1, attempt interpret. Handle time out?
		while (accumOperators.size() > 1){
			Operator op = accumOperators.get(accumOperators.size()-1);
			interprete(parseList);
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
		
		if (item.equals("-") && (prevItem != null || !isNumber(prevItem)){
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
	
	public void handleParenthesis(IterpreterContext context, String input, 
		List<Symbol> accumulatedPrecedence, List<Symbol> parseTree){
		// TODO		
	}	
}
