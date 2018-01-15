package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JDBCNavigations {

	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String Dbpassword = "12345";

	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;

	String sql = "select department_id, count(*) from employees group by department_id";

	@BeforeTest
	public void connectToDatabase() throws SQLException {

		connection = DriverManager.getConnection(URL, DbUserName, Dbpassword);
	}

	@Test
	public void getRowsCount() throws SQLException {

		statement = connection.createStatement();
		resultset = statement.executeQuery(sql);
		resultset.last();
		int rowsCount = resultset.getRow();
		System.out.println("number of rows returned " + rowsCount);
	}

	@Test(dependsOnMethods = { "getRowsCount" }, enabled = false)

	public void goReverse() throws SQLException {

		resultset.afterLast();
		while (resultset.previous()) {
			System.out.println(resultset.getString("last_name") + "earns " + resultset.getDouble("salary"));
		}
	}

	@Test(dependsOnMethods = { "getRowsCount" }, enabled = false)

	public void goToSpecificRow() throws SQLException {

		resultset.absolute(2);
		System.out.println("2 nd row " + resultset.getString("last_name") + "earns " + resultset.getDouble("salary"));
		resultset.absolute(1);
		System.out.println("1 st row " + resultset.getString("last_name") + "earns " + resultset.getDouble("salary"));
	}

	@Test
	public void printAll() throws SQLException {

		resultset.beforeFirst();

		while (resultset.next()) {

			int currentRowNum = resultset.getRow();
			int currentDepID = resultset.getInt("department_id");
			int currentCount = resultset.getInt("count(*)");

			System.out.println(currentRowNum + "\t" + currentDepID + "-->" + currentCount);
		}
	}

	@AfterTest

	public void closeConnections() throws SQLException {

		if (resultset != null) {
			resultset.close();
		}
		if (statement != null) {
			statement.close();

		}
		if (connection != null) {
			connection.close();
		}
	}

}
