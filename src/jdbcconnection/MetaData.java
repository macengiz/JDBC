package jdbcconnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetaData {

	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String DbPassword = "12345";

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	static String sql = "SELECT * from employees";

	public static void main(String[] args) throws SQLException {
		
		connection = DriverManager.getConnection(URL, DbUserName, DbPassword);
		DatabaseMetaData dbMetaData = connection.getMetaData();

		System.out.println(dbMetaData.getDatabaseProductName());
		System.out.println(dbMetaData.getDatabaseProductVersion());
		System.out.println(dbMetaData.getUserName());

		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		ResultSetMetaData rsMetaData = resultSet.getMetaData();

		// System.out.println(rsMetaData.getColumnCount());
		// System.out.println(rsMetaData.getColumnName(1) );
		// System.out.println(rsMetaData.getColumnName(2) );

		for (int colIndex = 1; colIndex <= rsMetaData.getColumnCount(); colIndex++) {
			System.out.println(rsMetaData.getColumnName(colIndex));

		}

		resultSet.close();
		statement.close();
		connection.close();

	}

}
