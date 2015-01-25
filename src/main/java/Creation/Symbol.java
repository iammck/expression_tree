import java.util.List;

public abstract class Symbol {
	public abstract ComponentNode build();
	public abstract boolean interpret(List<Symbol> interpretedSymbols);
	public abstract int comparedToSymbol(Symbol otherSymbol);
	public abstract void addToSymbols(List<Symbol> interpretedSymbols, List<Symbol> accumSymbols);	
}
