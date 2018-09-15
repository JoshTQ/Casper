package online.potters.impl.bukkit.utils;

import org.bukkit.ChatColor;

/**
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
public class ColorUtils {

	/**
	 * @param input String to be reformatted.
	 * @return The String Colorized with the '&' ColorChar.
	 */
	public static String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	/**
	 * @param input String to be reformatted.
	 * @return The String with all ColorCodes removed.
	 */
	public static String strip(String input) {
		return ChatColor.stripColor(input);
	}

	/**
	 * @param input String to check if it has ColorCodes.
	 * @return Whether or not the String has ColorCodes.
	 */
	public static boolean hasColorCodes(String input) {
		return colorize(input).equals(input);
	}

}
