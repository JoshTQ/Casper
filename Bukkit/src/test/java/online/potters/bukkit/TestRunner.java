package online.potters.bukkit;

import online.potters.bukkit.command.CommandTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
			CommandTest.class
		);

		for (Failure failure : result.getFailures()) {
			System.out.println("FAILED TEST: " + failure.toString());
		}

		System.out.println("Total failed: " + result.getFailureCount() + "/" + result.getRunCount());
		System.out.println("Success: " + result.wasSuccessful());
	}
}