package online.potters.common.utils;

import online.potters.impl.common.utils.RomanNumerals;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
public class RomanNumeralTest {

	@Test
	public void romanNumeralTestingValues() {
		RomanNumerals romanNumerals = new RomanNumerals();

		assertEquals("RomanNumeral 1: ", "X", romanNumerals.toNumeral(10));
		assertEquals("RomanNumeral 2: ", "MMXVIII", romanNumerals.toNumeral(2018));
		assertEquals("RomanNumeral 3: ", "XIX", romanNumerals.toNumeral(19));
		assertEquals("RomanNumeral 4: ", "MMMMCCCXXVII", romanNumerals.toNumeral(4327));

		assertEquals("RomanNumeral 5: ", 10, romanNumerals.toInt("X"));
		assertEquals("RomanNumeral 6: ", 2018, romanNumerals.toInt("MMXVIII"));
		assertEquals("RomanNumeral 7: ", 19, romanNumerals.toInt("XIX"));
		assertEquals("RomanNumeral 8: ", 4327, romanNumerals.toInt("MMMMCCCXXVII"));
		assertEquals("RomanNumeral 9:", -1, romanNumerals.toInt("JFN"));


	}
}
