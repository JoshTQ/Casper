package online.potters.common;

import online.potters.common.managers.InstanceManagerTest;
import online.potters.common.queue.QueueTest;
import online.potters.common.utils.DateUtilsTest;
import online.potters.common.utils.RomanNumeralTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
				RomanNumeralTest.class,
				DateUtilsTest.class,
				QueueTest.class,
				InstanceManagerTest.class
		);

		for (Failure failure : result.getFailures()) {
			System.out.println("FAILED TEST: " + failure.toString());
		}

		System.out.println("Total failed: " + result.getFailureCount() + "/" + result.getRunCount());
		System.out.println("Success: " + result.wasSuccessful());
	}
}
