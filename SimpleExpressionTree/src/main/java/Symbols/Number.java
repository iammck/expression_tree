import java.util.List;

public class Number extends Symbol {
	String number;
	public Number(String number){
		this.number = number;
	}
	
	public ComponentNode build(){
		return new NumberLeafNode(number);
	}
	
	public boolean interpret(List<Interpretable> interpretedList){
		interpretedList.add(this);
		return true;
	}
	
	public boolean evaluate(List<Evaluatable> evaluatedList){
		evaluatedList.add(this);
		return true;
	}
	
	public int precedenceComparedTo(Symbol otherSymbol){
		if (otherSymbol instanceof Number){
			return 0;
		} else {
			return -1;
		}
	}
	
	public void addToInterpreter(List<Interpretable> interpretedList, 
						List<Interpretable> pendingList){
		this.interpret(interpretedList);
	}
	
	public void addToEvaluator(List<Evaluatable> evaluatedList, 
					List<Evaluatable> pendingList){
	
		this.evaluate(evaluatedList);	
	}
	
	public Double toDouble(){
		return new Double(Double.valueOf(number));
	}
}
