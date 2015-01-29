public class SetTreeOrderCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new SetTreeOrderCommand(context, arg);
	}
}
