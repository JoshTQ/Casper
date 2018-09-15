package online.potters.common.utils;

import online.potters.impl.common.utils.RomanNumerals;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

		assertNull("RomanNumeral 5:", romanNumerals.toNumeral(-5));

	}
}
