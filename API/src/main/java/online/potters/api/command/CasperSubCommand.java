package online.potters.api.command;

public abstract class CasperSubCommand {

	private String command;

	private String permission;

	private String[] aliases;

	private int minimumArguments;

	private boolean consoleCommand;

	public CasperSubCommand(String command) {
		this.command = command;
		this.permission = null;
		this.aliases = new String[]{};
		this.minimumArguments = 0;
		this.consoleCommand = true;
	}

	public CasperSubCommand(String command, String permission) {
		this.command = command;
		this.permission = permission;
		this.aliases = new String[]{};
		this.minimumArguments = 0;
		this.consoleCommand = true;
	}

	public CasperSubCommand(String command, String permission, String[] aliases) {
		this.command = command;
		this.permission = permission;
		this.aliases = aliases;
		this.minimumArguments = 0;
		this.consoleCommand = true;
	}

	public CasperSubCommand(String command, String permission, String[] aliases, int minimumArguments) {
		this.command = command;
		this.permission = permission;
		this.aliases = aliases;
		this.minimumArguments = minimumArguments;
		this.consoleCommand = true;
	}

	public CasperSubCommand(String command, String permission, String[] aliases, int minimumArguments, boolean consoleCommand) {
		this.command = command;
		this.permission = permission;
		this.aliases = aliases;
		this.minimumArguments = minimumArguments;
		this.consoleCommand = consoleCommand;
	}

	public String getCommand() {
		return command;
	}

	public String getPermission() {
		return permission;
	}

	public String[] getAliases() {
		return aliases;
	}

	public int getMinimumArguments() {
		return minimumArguments;
	}

	public boolean isConsoleCommand() {
		return consoleCommand;
	}
}
