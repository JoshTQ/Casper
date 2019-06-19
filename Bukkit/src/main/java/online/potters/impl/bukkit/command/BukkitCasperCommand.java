package online.potters.impl.bukkit.command;

import com.google.common.base.Preconditions;
import online.potters.api.command.CasperCommand;
import online.potters.api.command.CasperSubCommand;
import online.potters.api.utils.DuelCallable;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BukkitCasperCommand extends Command implements CasperCommand, CommandExecutor {

	private Set<CasperSubCommand> subCommands;
	private String commandName;
	private Set<String> commandAliases;
	private String permission;
	private int minimumArguments;
	private boolean isConsoleCommand;
	private DuelCallable<CommandSender, String[]> commandHandler;

	private BukkitCasperCommand(Set<CasperSubCommand> subCommands, String commandName, Set<String> commandAliases, String permission, int minimumArguments, boolean isConsoleCommand, DuelCallable<CommandSender, String[]> commandHandler) {
		super(commandName);
		this.subCommands = subCommands;
		this.commandName = commandName;
		this.commandAliases = commandAliases;
		this.permission = permission;
		this.minimumArguments = minimumArguments;
		this.isConsoleCommand = isConsoleCommand;
		this.commandHandler = commandHandler;
	}

	@Override
	public boolean execute(CommandSender commandSender, String s, String[] args) {
		String commandEntered = args[0];

		if (!commandName.equalsIgnoreCase(commandEntered) && !commandAliases.contains(commandEntered.toLowerCase())) {
			return false;
		}

		Preconditions.checkNotNull(commandHandler, "CommandHandler for " + commandName + " is null!");

		if (!isConsoleCommand && commandSender instanceof ConsoleCommandSender) {
			commandSender.sendMessage("Sorry, you cannot run this command in console.");
			return false;
		}

		if (permission != null && !commandSender.hasPermission(permission)) {
			return false;
		}

		if (args.length < minimumArguments) {
			return false;
		}

		if (args.length == 1 || subCommands.size() == 0) {
			commandHandler.run(commandSender, args);
		}

		Optional<CasperSubCommand> subCommandExecuted = executeSubCommand(args[1]);

		if (subCommandExecuted.isPresent()) {
			//todo execute the subcommand
			return true;
		}

		commandHandler.run(commandSender, args);
		return true;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		return command.getName().equalsIgnoreCase(commandName) && execute(commandSender, s, args);
	}

	@Override
	public String getCommand() {
		return this.commandName;
	}

	@Override
	public Set<String> getCommandAliases() {
		return this.commandAliases;
	}

	@Override
	public int getMinimumArguments() {
		return this.minimumArguments;
	}

	@Override
	public boolean isConsoleCommand() {
		return this.isConsoleCommand;
	}

	@Override
	public Set<CasperSubCommand> getSubCommands() {
		return this.subCommands;
	}

	@Override
	public Optional<CasperSubCommand> executeSubCommand(String command) {
		if (subCommands.size() == 0) return null;

		Optional<CasperSubCommand> executedCommand = subCommands.stream().filter(subCommand -> subCommand.getCommand().equalsIgnoreCase(command)).findFirst();

		return executedCommand;
	}

	public static CommandBuilder commandBuilder() {
		return new CommandBuilder();
	}

	public static class CommandBuilder {
		private Set<CasperSubCommand> subCommands = new HashSet<>();
		private String commandName = "noCommandEntered";
		private Set<String> commandAliases = new HashSet<>();
		private String permission = null;
		private int minimumArguments = -1;
		private boolean isConsoleCommand = true;
		private DuelCallable<CommandSender, String[]> commandHandler = null;

		public CommandBuilder subCommands(CasperSubCommand... subCommands) {
			this.subCommands = new HashSet<>(Arrays.asList(subCommands));
			return this;
		}

		public CommandBuilder name(String commandName) {
			this.commandName = commandName;
			return this;
		}

		public CommandBuilder aliases(String... aliases) {
			this.commandAliases = new HashSet<>(Arrays.asList(aliases));

			return this;
		}

		public CommandBuilder permission(String permission) {
			this.permission = permission;

			return this;
		}

		public CommandBuilder minimumArgs(int minimumArguments) {
			this.minimumArguments = minimumArguments;

			return this;
		}

		public CommandBuilder consoleCommand(boolean consoleCommand) {
			isConsoleCommand = consoleCommand;

			return this;
		}

		public CommandBuilder handler(DuelCallable<CommandSender, String[]> commandHandler) {
			this.commandHandler = commandHandler;

			return this;
		}

		public CasperCommand build(JavaPlugin javaPlugin) {
			BukkitCasperCommand casperCommand = new BukkitCasperCommand(subCommands, commandName, commandAliases, permission, minimumArguments, isConsoleCommand, commandHandler);

			try {
				Field bukkitCommandMap = javaPlugin.getServer().getClass().getDeclaredField("commandMap");
				bukkitCommandMap.setAccessible(true);
				CommandMap commandMap = (CommandMap) bukkitCommandMap.get(javaPlugin.getServer());
				commandMap.register(commandName, casperCommand);
				bukkitCommandMap.setAccessible(false);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}

			return casperCommand;
		}
	}
}