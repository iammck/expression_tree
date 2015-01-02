public class Number extends Symbol {
	public boolean interpret(List<Symbol> parsedList){
		parsedList.add(this);
		return true;
	}
}
