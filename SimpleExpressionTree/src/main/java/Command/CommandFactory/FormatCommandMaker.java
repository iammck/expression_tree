public class FormatCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new FormatCommand(context, arg);
	}
}
