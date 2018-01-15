package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionDemo {

	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String Dbpassword = "12345";

	public static void main(String[] args) {

		try {
			Connection connection = DriverManager.getConnection(URL, DbUserName, Dbpassword);
			System.out.println("mysql database connection succesful");

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("mysql database connection attempt failed");
			e.printStackTrace();
		}
	}
}
