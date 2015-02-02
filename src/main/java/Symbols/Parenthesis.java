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
	 * If a '(', will add itself to pendingList. Otherwise, it is a ')' so iterate through
	 * pendingList calling interpret() then remove matching '(' from pendingList.
	 */	 
	public void addToInterpreter(List<Symbol> interpretedList, List<Symbol> pendingList){		
		// if symbol is (, put into pendingList
		if (symbol.equals("(")){
			pendingList.add(this);
			return;
		}
		// if pendingList is empty, this ) is missing a (
		if (pendingList.size() == 0){
			throw new InvalidInputException(
				"this ) is missing a (" );
		}
		// while there are pendingList items and the last is not (
		Symbol lastSymbol = pendingList.get(pendingList.size() - 1);
		while (lastSymbol.interpret(interpretedList)){
			// remove it from the accumulated operators.
			pendingList.remove(lastSymbol);
			// if out of symbols, this ) is missing a (
			if (pendingList.size() == 0){
				throw new InvalidInputException(
					"this ) is missing a (");
			}
			// get the next symbol
			lastSymbol = pendingList.get(pendingList.size() - 1);
		}
		// if the last symbol is a (, then remove it.
		if ( lastSymbol instanceof Parenthesis){
			if( ((Parenthesis) lastSymbol).symbol.equals("("))
				pendingList.remove(lastSymbol);
		}
	}
	
	public boolean interpret(List<Symbol> interpretedList){
		return false;
	}
	
	public int precedenceComparedToSymbol(Symbol otherSymbol){
		if (otherSymbol instanceof Parenthesis){
			return 0;
		} else {
			return 1;
		}
	}
}
