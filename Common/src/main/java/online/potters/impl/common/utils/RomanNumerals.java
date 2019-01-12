package online.potters.impl.common.utils;

import online.potters.api.utils.IRomanNumerals;

import java.util.TreeMap;

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
 **/
public class RomanNumerals implements IRomanNumerals {

	private TreeMap<Integer, String> numerals;

	public RomanNumerals() {
		numerals = new TreeMap<>();

		numerals.put(1000, "M");
		numerals.put(900, "CM");
		numerals.put(500, "D");
		numerals.put(400, "CD");
		numerals.put(100, "C");
		numerals.put(90, "XC");
		numerals.put(50, "L");
		numerals.put(40, "XL");
		numerals.put(10, "X");
		numerals.put(9, "IX");
		numerals.put(5, "V");
		numerals.put(4, "IV");
		numerals.put(1, "I");
	}

	//

	@Override
	public int toInt(String toConvert) {
		return 0;
	}

	public String toNumeral(int toConvert) {
		if (toConvert < 0) {
			return null;
		}

		int key = numerals.floorKey(toConvert);
		if (toConvert == key) {
			return numerals.get(toConvert);
		}
		return numerals.get(key) + toNumeral(toConvert - key);
	}
}
