import java.util.List;

public class Parenthesis extends Symbol{
	private String symbol;
	
	public Parenthesis(String symbol){
		this.symbol = symbol;
	}
	
	public ComponentNode build(){
		return null;
	}
	
	/*
	 * will add itself as top accum symbol if (. Otherwise, iterates through
	 * accumSymbols calling interpret() then removing from accumOperators.
	 */	 
	public void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){		
		// if symbol is (, put into accumulated operators
		if (symbol.equals("(")){
			accumSymbols.add(this);
			return;
		}
		// if there are no accumulated symbols, this ) is missing a (
		if (accumSymbols.size() == 0){
			throw new InvalidInputException(
				"this ) is missing a (", this,
				interpretedSymbols, accumSymbols);
		}
		// while there are accumulated operators and the top is not (
		Symbol topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		while (topSymbol.interpret(interpretedSymbols)){
			// remove it from the accumulated operators.
			accumSymbols.remove(topSymbol);
			// if out of symbols, this ) is missing a (
			if (accumSymbols.size() == 0){
				throw new InvalidInputException(
					"this ) is missing a (", this,
					interpretedSymbols, accumSymbols);
			}
			// get the next symbol
			topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		}
		// if the last top operator is a (, then remove it.
		if ( topSymbol instanceof Parenthesis){
			if( ((Parenthesis) topSymbol).symbol.equals("("))
				accumSymbols.remove(topSymbol);
		}
	}
	
	public boolean interpret(List<Symbol> interpretedSymbols){
		return false;
	}
	
	public int comparedToSymbol(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis){
			return 0;
		} else {
			return 1;
		}
	}
}
