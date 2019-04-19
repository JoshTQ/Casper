package online.potters.common.managers;

import online.potters.impl.common.managers.InstanceManager;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class InstanceManagerTest {

	@Test
	public void testManagers() {
		InstanceManager<TestData> manager = new InstanceManager<>();

		TestData dataOne = new TestData("potters", "testing");
		TestData dataTwo = new TestData("josh", "none");

		manager.cacheObject(dataOne);
		manager.cacheObject(dataTwo);

		assertTrue(manager.containsCachedObject(dataOne));
		assertFalse(manager.containsCachedObject(new TestData("Unknown", "Hunter")));
		assertEquals(manager.getCachedObject(testData -> testData.getPassword().equals("testing")), Optional.of(dataOne));

		assertNull(manager.getCachedObject(testData -> testData.getPassword().equals("test")).orElse(null));
		manager.shutdown();
	}

	private class TestData {

		private String username;
		private String password;

		public TestData(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}
	}
}