package online.potters.impl.common.utils;

import online.potters.api.utils.IDateUtils;
import online.potters.impl.common.databases.sql.DatabaseConnection;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * .-----.
 * .' -   - '.
 * /  .-. .-.  \  	   .--.      .---.      .--.       .-..     .--.    ___ .-.
 * |  | | | |  |  	  /    \    / .-, \   /  _  \     /    \   /    \  (   )   \
 * \ \o/ \o/ /   	 |  .-. ;  (__) ; |  . .' `. ;   ' .-,  ; |  .-. ;  | ' .-. ;
 * _/    ^    \_  	 |  |(___)   .'`  |  | '   | |   | |  . | |  | | |  |  / (___)
 * | \  '---'  / |  	 |  |       / .'| |  _\_`.(___)  | |  | | |  |/  |  | |
 * / /`--. .--`\ \  	 |  | ___  | /  | | (   ). '.    | |  | | |  ' _.'  | |
 * / /'---` `---'\ \	 |  '(   ) ; |  ; |  | |  `\ |   | |  ' | |  .'.-.  | |
 * '.__.       .__.'	 '  `-' |  ' `-'  |  ; '._,' '   | `-'  ' '  `-' /  | |
 * `|     |`    	  `.__,'   `.__.'_.   '.___.'    | \__.'   `.__.'  (___)
 * |     \     	                                 | |
 * \      '--.   	                                (___)
 * '.        `\
 * `'---.   |
 * ,__) /
 * `..'
 *
 * @author PottersMC (2018)
 **/
public class DateUtils implements IDateUtils {

	@Override
	public boolean hasPassed(Calendar time) {
		return System.currentTimeMillis() >= time.getTimeInMillis();
	}

	@Override
	public boolean hasPassed(long unixTime) {
		return System.currentTimeMillis() >= unixTime;
	}

	@Override
	public String formatDate(Calendar time, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time.getTime());
	}
}
