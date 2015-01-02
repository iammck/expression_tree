public class Number extends Symbol {
	String number;
	
	public setNumber(String number){
		this.number = number;
	}
	
	public boolean interpret(List<Symbol> parsedList){
		parsedList.add(this);
		return true;
	}
}
