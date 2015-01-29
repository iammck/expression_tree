public class SetInputFormatCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new SetInputFormatCommand(context, arg);
	}
}
