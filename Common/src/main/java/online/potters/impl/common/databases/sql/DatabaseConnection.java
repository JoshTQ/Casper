package online.potters.impl.common.databases.sql;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import online.potters.api.utils.Callback;
import online.potters.api.storage.databases.ISQLStorage;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DatabaseConnection implements ISQLStorage {

	private String address;
	private int port;
	private String username;
	private Optional<String> passwordOptional;
	private String database;

	private HikariDataSource hikariDataSource;

	private ExecutorService executorService;

	private DatabaseConnection() {
		executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("caspar-sql-%").build());
	}

	private void connect() {
		HikariConfig hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl("jdbc:mysql://%host%:%port%/%database%?autoReconnect=true"
				.replace("%host%", address)
				.replace("%port%", String.valueOf(port))
				.replace("%database%", database));
		hikariConfig.setUsername(username);
		passwordOptional.ifPresent(hikariConfig::setPassword);
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMaxLifetime(5000);

		hikariDataSource = new HikariDataSource(hikariConfig);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return hikariDataSource.getConnection();
	}

	@Override
	public boolean execute(String query, Callback statement) {
		return executorService.submit(() -> {
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(query);
				statement.run(preparedStatement);
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbUtils.closeQuietly(preparedStatement);
				DbUtils.closeQuietly(connection);
			}
		}).isDone();
	}

	@Override
	public boolean executeQuery(String query, Callback statement, Callback result) {
		return executorService.submit(() -> {
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(query);
				statement.run(preparedStatement);
				resultSet = preparedStatement.executeQuery();
				result.run(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbUtils.closeQuietly(preparedStatement);
				DbUtils.closeQuietly(resultSet);
				DbUtils.closeQuietly(connection);
			}
		}).isDone();
	}

	@Override
	public void close() {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}

		hikariDataSource.close();
	}

	static class Builder {

		private int port = 3306;
		private String hostAddress = "localhost";
		private String password = null;
		private String username = "root";
		private String database = "minecraft";

		public Builder atPort(int port) {
			this.port = port;
			return this;
		}

		public Builder atHost(String hostAddress) {
			this.hostAddress = hostAddress;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withDatabase(String database) {
			this.database = database;
			return this;
		}

		public DatabaseConnection build() {
			DatabaseConnection databaseConnection = new DatabaseConnection();

			databaseConnection.address = this.hostAddress;
			databaseConnection.port = this.port;
			databaseConnection.username = this.username;
			databaseConnection.passwordOptional = Optional.ofNullable(this.password);
			databaseConnection.database = this.database;

			databaseConnection.connect();

			return databaseConnection;
		}
	}
}
