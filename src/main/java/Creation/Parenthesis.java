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
	public void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols){		
		// if symbol is (, put into accumulated operators
		if (symbol.equals("(")){
			accumSymbols.add(this);
			return;
		}
		// while there are accumulated operators and the top is not (
		Symbol topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		while (topSymbol.interpret(parsedSymbols)){
			// remove it from the accumulated operators and get next.
			accumSymbols.remove(topSymbol);
			topSymbol = accumSymbols.get(accumSymbols.size() - 1);
		}
		// if the last top operator is a (, then remove it.
		if ( topSymbol instanceof Parenthesis){
			if( ((Parenthesis) topSymbol).symbol.equals("("))
				accumSymbols.remove(topSymbol);
		}
	}
	
	public boolean interpret(List<Symbol> parseSymbols){
		return false;
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis)
			return true;
		return false;
	}
}
