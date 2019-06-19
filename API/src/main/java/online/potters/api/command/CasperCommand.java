package online.potters.api.command;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
 *
 *		     .-----.
 *		   .' -   - '.
 *		  /  .-. .-.  \  	   .--.      .---.      .--.       .-..     .--.    ___ .-.
 *		  |  | | | |  |  	  /    \    / .-, \   /  _  \     /    \   /    \  (   )   \
 *		   \ \o/ \o/ /   	 |  .-. ;  (__) ; |  . .' `. ;   ' .-,  ; |  .-. ;  | ' .-. ;
 *		  _/    ^    \_  	 |  |(___)   .'`  |  | '   | |   | |  . | |  | | |  |  / (___)
 * 		| \  '---'  / |  	 |  |       / .'| |  _\_`.(___)  | |  | | |  |/  |  | |
 * 		/ /`--. .--`\ \  	 |  | ___  | /  | | (   ). '.    | |  | | |  ' _.'  | |
 *		/ /'---` `---'\ \	 |  '(   ) ; |  ; |  | |  `\ |   | |  ' | |  .'.-.  | |
 *		'.__.       .__.'	 '  `-' |  ' `-'  |  ; '._,' '   | `-'  ' '  `-' /  | |
 *		    `|     |`    	  `.__,'   `.__.'_.   '.___.'    | \__.'   `.__.'  (___)
 *		     |     \     	                                 | |
 *		    \      '--.   	                                (___)
 *  		    '.        `\
 * 	 	      `'---.   |
 *  	 	        ,__) /
 *  	  	        `..'
 *
 * @author PottersMC (2018)
 */
public interface CasperCommand {

	/**
	 * @return The root command's label
	 */
	String getCommand();

	/**
	 * @return The Permission required to execute the root command
	 */
	default String getPermission() {
		return null;
	}

	/**
	 * @return All the Aliases that can also execute commandHandler()
	 */
	default Set<String> getCommandAliases() {
		return new HashSet<>();
	}


	/**
	 * @return The Minimum amount of Arguments required to execute the root command
	 */
	int getMinimumArguments();

	/**
	 * @return Whether or not the console can execute the root command
	 */
	default boolean isConsoleCommand() {
		return true;
	}

	/**
	 * @return A List of all the CasperSubCommands attached to the root command.
	 */
	default Set<CasperSubCommand> getSubCommands() {
		return new HashSet<>();
	}

	default void registerCommand(JavaPlugin javaPlugin) {}

	//todo BungeeCord

	/**
	 * @param command The command (argument) being executed at that time.
	 * @return The Class of the CasperSubCommand being executed.
	 */
	Optional<CasperSubCommand> executeSubCommand(String command);

}
