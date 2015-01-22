public class SetFormatCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new SetFormatCommand(context, arg);
	}
}
