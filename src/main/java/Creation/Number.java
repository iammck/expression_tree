import java.util.List;

public class Number extends Symbol {
	String number;
	public Number(String number){
		this.number = number;
	}
	
	public ComponentNode build(){
		return new NumberLeafNode(number);
	}
	
	public boolean interpret(List<Symbol> interpretedSymbols){
		interpretedSymbols.add(this);
		return true;
	}
	
	public boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol){
		return true;
	}
	
	public void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){
		this.interpret(interpretedSymbols);
	}
	
}
