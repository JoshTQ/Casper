package online.potters.common.utils;

import online.potters.impl.common.utils.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.*;

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
public class DateUtilsTest {

	@Test
	public void dateUtilsExpiredDate() {
		DateUtils dateUtils = new DateUtils();
		Calendar testOne = Calendar.getInstance();

		assertTrue("ExpiredDate Test 1: ", dateUtils.hasPassed(testOne));

		Calendar testTwo = Calendar.getInstance();

		testTwo.set(2017, 5, 3);

		assertTrue("ExpiredDate Test 2: ", dateUtils.hasPassed(testTwo));

		Calendar testThree = Calendar.getInstance();

		testThree.set(2019, 2, 3);

		assertFalse("ExpiredDate Test 3:  ", dateUtils.hasPassed(testThree));

		Calendar testFour = new GregorianCalendar();

		testFour.set(2018, testFour.getTime().getMonth(), testFour.getTime().getDate() + 1);

		assertFalse("ExpiredDate Test 4: ", dateUtils.hasPassed(testFour));
	}

	@Test
	public void dateUtilsExpiredUnix() {
		DateUtils dateUtils = new DateUtils();

		long timePassed = System.currentTimeMillis();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(dateUtils.hasPassed(timePassed));

		assertTrue(dateUtils.hasPassed(System.currentTimeMillis() - 5000));

		assertFalse(dateUtils.hasPassed(System.currentTimeMillis() + 500000));

		assertFalse(dateUtils.hasPassed(System.currentTimeMillis() + 1));
	}

	@Test
	public void dateUtilsFormat() {
		DateUtils dateUtils = new DateUtils();

		Calendar testOne = new GregorianCalendar(2018, 4, 5);
		assertEquals("DateFormat 1: ", "2018-05-05", dateUtils.formatDate(testOne, "yyyy-MM-dd"));

		Calendar testTwo = new GregorianCalendar(2020, 5, 30);
		assertEquals("DateFormat 2: ", "2020-06-30", dateUtils.formatDate(testTwo, "yyyy-MM-dd"));

		Calendar testThree = new GregorianCalendar(2013, 4, 5);
		assertEquals("DateFormat 3: ", "2013-05-05", dateUtils.formatDate(testThree, "yyyy-MM-dd"));

		Calendar testFour = new GregorianCalendar(2018, 4, 5, 6, 30, 30);
		assertEquals("DateFormat 4: ", "2018-05-05 06:30:30", dateUtils.formatDate(testFour, "yyyy-MM-dd hh:mm:ss"));
	}
}
