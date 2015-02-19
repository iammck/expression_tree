public class SetTreeOrderCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg)
						throws InvalidCommandException {
		return new SetTreeOrderCommand(context, arg);
	}
}
