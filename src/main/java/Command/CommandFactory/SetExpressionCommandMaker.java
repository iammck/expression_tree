public class SetExpressionCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new SetExpressionCommand(context, arg);
	}
}
