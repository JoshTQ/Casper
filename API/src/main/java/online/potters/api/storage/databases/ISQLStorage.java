package online.potters.api.storage.databases;

import online.potters.api.utils.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public interface ISQLStorage {

	/**
	 * @return Existing connection to the Database.
	 */
	Connection getConnection() throws SQLException;

	/**
	 * @param query     The SQL query you wish to execute.
	 * @param statement Returns a PreparedStatement, set any placeholders here.
	 */
	void execute(String query, Callback<PreparedStatement> statement);

	/**
	 * @param query     The SQL query you wish to execute.
	 * @param statement Returns a PreparedStatement, set any placeholders here.
	 * @param result    Result of the PreparedStatement.
	 */
	void executeQuery(String query, Callback<PreparedStatement> statement, Callback<ResultSet> result);

	/**
	 * Close the connection to the Database.
	 */
	void close();

}
