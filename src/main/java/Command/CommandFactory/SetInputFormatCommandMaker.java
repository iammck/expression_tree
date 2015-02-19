public class SetInputFormatCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new SetInputFormatCommand(context, arg);
	}
}
