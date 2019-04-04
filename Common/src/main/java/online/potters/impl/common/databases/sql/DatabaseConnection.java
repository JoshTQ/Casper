package online.potters.impl.common.databases.sql;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.NonNull;
import online.potters.api.storage.databases.ISQLStorage;
import online.potters.api.utils.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
 * @author PottersMC (2019)
 **/
public class DatabaseConnection implements ISQLStorage {

	private String address;
	private int port;
	private String username;
	private Optional<String> passwordOptional;
	private String database;

	private HikariDataSource hikariDataSource;

	@Getter
	private ExecutorService executorService;

	private DatabaseConnection() {
		executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("caspar-sql-%d").build());
	}

	private void connect() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://{host}:{port}/{database}?autoReconnect=true"
				.replace("{host}", address)
				.replace("{port}", String.valueOf(port))
				.replace("{database}", database));

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
			try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
				statement.run(preparedStatement);
				preparedStatement.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}).isDone();
	}

	@Override
	public boolean executeQuery(String query, Callback statement, Callback result) {
		return executorService.submit(() -> {
			try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
				statement.run(preparedStatement);
				result.run(preparedStatement.executeQuery());
			} catch (SQLException e) {
				throw new RuntimeException(e);
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
		} finally {
			hikariDataSource.close();
		}
	}

	public static class Builder {

		private int port = 3306;
		private String hostAddress;
		private String password = null;
		private String username = "root";
		private String database = "minecraft";

		public Builder(@NonNull String hostAddress) {
			this.hostAddress = hostAddress;
		}

		public Builder atPort(int port) {
			this.port = port;
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
			System.out.println("Successfully ported data. Connecting....");

			databaseConnection.connect();

			return databaseConnection;
		}
	}
}
