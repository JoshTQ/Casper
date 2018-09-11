package online.potters.api.storage.databases;

import com.sun.istack.internal.NotNull;
import online.potters.api.utils.Callback;

import java.sql.Connection;

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
*
*/
public interface SQLStorage {

	Connection getConnection();

	void execute(@NotNull String query, Object... placeholders);

	Callback execute(@NotNull Callback callback, @NotNull String query, Object... placeholders);

	void close();

}
