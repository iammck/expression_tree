import java.util.List;

public class InvalidInputException extends RuntimeException{
	
	Symbol currentSymbol;
	List<Symbol> interpretedSymbols;
	List<Symbol> accumSymbols;
	
	
	public InvalidInputException(String messege, Symbol currentSymbol,
			List<Symbol> interpretedSymbols, List<Symbol> accumSymbols){
		super(messege);
		this.currentSymbol = currentSymbol;
		this.interpretedSymbols = interpretedSymbols;
		this.accumSymbols = accumSymbols;
	}
	
	public Symbol getCurrentSymbol(){
		return currentSymbol;
	}
	
	public List<Symbol> getInterpretedSymbols(){
		return interpretedSymbols;
	}
	
	public List<Symbol> getAccumSymbols(){
		return accumSymbols;
	}
}
