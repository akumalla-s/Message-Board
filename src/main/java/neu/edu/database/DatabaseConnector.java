package neu.edu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static DatabaseConnector instance = null;
	private Connection connection;

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/miniproject";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "root";

	private DatabaseConnector() {
		try {
			Class.forName(JDBC_DRIVER);
			String url = DB_URL;
			String user = USER;
			String password = PASS;
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseConnector getInstance() {

		if (instance == null) {
			instance = new DatabaseConnector();
		}

		try {
			if (instance.getConnection().isClosed()) {
				instance = new DatabaseConnector();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	public Connection getConnection() {
		return connection;
	}
}
