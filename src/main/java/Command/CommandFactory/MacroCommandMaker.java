public class MacroCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		return new MacroCommand(context, arg);
	}
}
