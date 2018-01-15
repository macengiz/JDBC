package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class UsingDBUtil {

	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String DbPassword = "1234";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = DriverManager.getConnection(URL, DbUserName, DbPassword);
		statement = connection.createStatement();
		resultSet = statement.executeQuery("Select state_province from locations where country_id='US'");

		while (resultSet.next()) {
			System.out.println(resultSet.getString("state_province"));
		}
	}

	@Test
	public void test() {

	}

}
