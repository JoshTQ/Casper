package online.potters.api.command;

import java.util.Arrays;
import java.util.Optional;

public abstract class CasperCommand {

	private String command;

	private String permission;

	private String[] aliases;

	private int minimumArguments;

	private boolean consoleCommand;

	private CasperSubCommand[] subCommands;

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 *
	 */
	public CasperCommand(String command) {
		this(command, null, new String[]{}, 0, true, new CasperSubCommand[]{});
 	}

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 * @param permission The Permission required to access the root command.
	 *
	 */
	public CasperCommand(String command, String permission) {
		this(command, permission, new String[]{}, 0, true, new CasperSubCommand[]{});
	}

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 * @param permission The Permission required to access the root command.
	 * @param aliases All the aliases that will also execute commandHandler()
	 *
	 */
	public CasperCommand(String command, String permission, String[] aliases) {
		this(command, permission, aliases, 0, true, new CasperSubCommand[]{});
	}

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 * @param permission The Permission required to access the root command.
	 * @param aliases All the aliases that will also execute commandHandler()
	 * @param minimumArguments The Minimum Arguments required to execute the command.
	 *
	 */
	public CasperCommand(String command, String permission, String[] aliases, int minimumArguments) {
		this(command, permission, aliases, minimumArguments, true, new CasperSubCommand[]{});

	}

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 * @param permission The Permission required to access the root command.
	 * @param aliases All the aliases that will also execute commandHandler()
	 * @param minimumArguments The Minimum Arguments required to execute the command.
	 * @param consoleCommand  Whether or not the console can execute this command.
	 *
	 */
	public CasperCommand(String command, String permission, String[] aliases, int minimumArguments, boolean consoleCommand) {
		this(command, permission, aliases, minimumArguments, consoleCommand, new CasperSubCommand[]{});
	}

	/**
	 *
	 * @param command The Main Command that you want the command to be registered by.
	 * @param permission The Permission required to access the root command.
	 * @param aliases All the aliases that will also execute commandHandler()
	 * @param minimumArguments The Minimum Arguments required to execute the command.
	 * @param consoleCommand  Whether or not the console can execute this command.
	 * @param subCommands The CasperSubCommands to be registered to the root command.
	 *
	 */
	public CasperCommand(String command, String permission, String[] aliases, int minimumArguments, boolean consoleCommand, CasperSubCommand[] subCommands) {
		this.command = command;
		this.permission = permission;
		this.aliases = aliases;
		this.minimumArguments = minimumArguments;
		this.consoleCommand = consoleCommand;
		this.subCommands = subCommands;
	}

	/**
	 *
	 * @return The root command's label
	 *
	 */
	public String getCommand() {
		return command;
	}

	/**
	 *
	 * @return The Permission required to execute the root command
	 *
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 *
	 * @return All the Aliases that can also execute commandHandler()
	 *
	 */
	public String[] getAliases() {
		return aliases;
	}

	/**
	 *
	 * @return The Minimum amount of Arguments required to execute the root command
	 *
	 */
	public int getMinimumArguments() {
		return minimumArguments;
	}

	/**
	 *
	 * @return Whether or not the console can execute the root command
	 *
	 */
	public boolean isConsoleCommand() {
		return consoleCommand;
	}

	/**
	 *
	 * @return A List of all the CasperSubCommands attached to the root command.
	 *
	 */
	public CasperSubCommand[] getSubCommands() {
		return subCommands;
	}

	/**
	 *
	 * @param command The command (argument) being executed at that time.
	 * @return The Class of the CasperSubCommand being executed.
	 *
	 */
	public CasperSubCommand executeSubCommand(String command) {
		if (subCommands == null) return null;

		Optional<CasperSubCommand> executedCommand = Arrays.stream(subCommands).filter(subCommand -> subCommand.getCommand().equalsIgnoreCase(command)).findFirst();

		return executedCommand.orElse(null);
	}

	/**
	 *
	 * @return Whether or not the command was successfully executed.
	 *
	 */
	abstract boolean commandHandler();

}
