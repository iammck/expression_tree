public class HelpCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new HelpCommand(context, arg);
	}
}
