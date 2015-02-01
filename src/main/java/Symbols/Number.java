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
	
	public int precedenceComparedToSymbol(Symbol otherSymbol){
		if (otherSymbol instanceof Number){
			return 0;
		} else {
			return -1;
		}
	}
	
	public void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){
		this.interpret(interpretedSymbols);
	}
	
}
