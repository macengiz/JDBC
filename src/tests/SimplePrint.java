package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jdbcconnection.DBUtility;
import jdbcconnection.DBUtility.DBType;

public class SimplePrint {

	@BeforeClass
	public void beforeClass() throws SQLException {
		DBUtility.establishConnection(DBType.MYSQL);
	}

	@AfterClass
	public void afterClass() {
		DBUtility.closeConnections();
	}

	@Test

	public void SimplePrintTest() throws SQLException {

		String query = "Select * from locations where country_id='US'";
		List<String[]> myresult = DBUtility.runSQLQuery(query);
		// System.out.println(myresult);

		for (int i = 0; i < myresult.size(); i++) {
			String[] eachItem = myresult.get(i);
			// System.out.println(eachItem[0] + "--" + eachItem[1]);
			for (String column : eachItem) {
				System.out.print(column + "--");
			}
			System.out.println();
		}
	}

	@Test

	public void simplePrintOriginal() throws SQLException {

		String query = "Select * from locations where country_id='US'";
		Connection con = DBUtility.getConnection(DBType.MYSQL);
		Statement stmnt = con.createStatement();
		ResultSet resultSet = stmnt.executeQuery(query);

		while (resultSet.next()) {
			String country_id = resultSet.getString("country_id");
			String state = resultSet.getString("state_province");
			System.out.println(country_id + "-----" + state);

		}
	}
}
