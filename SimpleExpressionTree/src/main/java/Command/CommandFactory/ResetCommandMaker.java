public class ResetCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new ResetCommand(context, arg);
	}
}
