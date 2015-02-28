public interface CommandFactory{
	Command makeCommand(String commandName, String arg) throws InvalidCommandException;
}
