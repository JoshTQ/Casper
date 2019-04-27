package online.potters.common.databases;

import online.potters.impl.common.databases.sql.DatabaseConnection;
import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class SQLStorageTest {

	@Test
	public void databaseConnect() {
		DatabaseConnection databaseConnection = null;

		try {
			databaseConnection = new DatabaseConnection.Builder("127.0.0.1")
					.atPort(3306)
					.withDatabase("testDatabase")
					.withUsername("root")
					.withPassword("testPassword")
					.build();

			databaseConnection.executeQuery("SELECT * FROM testing", null, resultSet -> {
				try {
					System.out.println("Outputting Data ...");
					while (resultSet.next()) {
						System.out.println("Found data: " + resultSet.getString("test"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

		} finally {
			if (databaseConnection != null) {
				databaseConnection.close();
			} else {
				fail("DatabaseConnection instance was null!");
			}
		}

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (!databaseConnection.getExecutorService().isTerminated() || !databaseConnection.getExecutorService().isShutdown()) {
			fail("ExecutorService was not shutdown.");
		}
	}
}
