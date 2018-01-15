package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatement {

	private static String URL = "jdbc:mysql://localhost:3306/hr";
	private static String DbUserName = "root";
	private static String Dbpassword = "12345";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {
			connection = DriverManager.getConnection(URL, DbUserName, Dbpassword);
			System.out.println("connected my sql database");

			statement = connection.createStatement();

			resultset = statement.executeQuery("select * from countries");

			while (resultset.next()) {
				System.out
						.println(resultset.getString("country_name") + "'s id is " + resultset.getString("country_id"));
			}

			resultset.close();

			System.out.println("****************** query 2 on the way****************");

			resultset = statement.executeQuery(
					"select last_name, department_name from employees e join departments d on e.department_id=d.department_id ");
			while (resultset.next()) {
				System.out.println(
						resultset.getString("last_name") + "works in " + resultset.getString("department_name"));
			}

		} catch (SQLException e) {

			System.out.println("something went wrong");
			e.printStackTrace();

		} finally {

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
}
