import java.util.List;

public class Number extends Symbol {
	String number;
	public Number(String number){
		this.number = number;
	}
	
	public ComponentNode build(){
		return new NumberLeafNode(number);
	}
	
	public boolean interpret(List<Symbol> interpretedList){
		interpretedList.add(this);
		return true;
	}
	
	public boolean evaluate(List<Evaluatable> evaluatedList){
		evaluatedList.add(this);
		return true;
	}
	
	public int precedenceComparedToSymbol(Symbol otherSymbol){
		if (otherSymbol instanceof Number){
			return 0;
		} else {
			return -1;
		}
	}
	
	public void addToInterpreter(List<Symbol> interpretedList, 
						List<Symbol> pendingList){
		this.interpret(interpretedList);
	}
	
	public void addToEvaluator(List<Evaluatable> evaluatedList, 
					List<Evaluatable> pendingList){
	
		this.evaluate(evaluatedList);	
	}
	
	public double toDouble(){
		return Double.valueOf(number);
	}
}
