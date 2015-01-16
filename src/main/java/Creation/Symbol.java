import java.util.List;

public abstract class Symbol {
	public abstract ComponentNode build();
	public abstract boolean interpret(List<Symbol> parseArray);
	public abstract boolean hasLessOrEqualPrecedenceOver(Symbol otherSymbol);
	public abstract void addToSymbols(List<Symbol> parsedSymbols, List<Symbol> accumSymbols);
	
}
