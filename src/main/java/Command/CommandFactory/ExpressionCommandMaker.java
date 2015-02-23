public class ExpressionCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new ExpressionCommand(context, arg);
	}
}
