import java.util.List;
import java.util.ArrayList;

public class MacroCommandMaker implements CommandMaker {
	public Command makeCommand(ExpressionTreeContext context, String arg){
		List<Command> commands = new ArrayList<Command>();
		commands.add(new SetFormatCommand(context, "in-fix"));
		commands.add(new SetExpressionCommand(context, arg));
		commands.add(new EvaluateCommand(context, "in-order"));
		commands.add(new QuitCommand(context, null));
		return new MacroCommand(context, commands);
	}
}
