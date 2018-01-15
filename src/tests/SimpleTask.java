package tests;

import java.sql.SQLException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jdbcconnection.DBUtility;
import jdbcconnection.DBUtility.DBType;

public class SimpleTask {

	@BeforeClass
	public void beforeClass() throws SQLException {
		DBUtility.establishConnection(DBType.MYSQL);
	}

	@AfterClass
	public void afterClass() {
		DBUtility.closeConnections();
	}

	@Test
	public void testSum() {

		/**
		 * create a sql query to get min_salary, max_salary and sum of both from
		 * jobs table
		 * 
		 * and assert your sum is actually equal to sum of two number num1 =
		 * yourStrArr[0] num2 = yourStrArr[1] expectedResult = yourStrArr[2]
		 * actualResult = num1+num2
		 */

		String query = "Select min_salary, max_salary, (min_salary+max_salary) as mySum from jobs";
		List<String[]> sumOfBoth = DBUtility.runSQLQuery(query);
		// for (String [] str : sumOfBoth) {
		// Integer min = Integer.parseInt(s[0]);
		boolean wholeSum = true;

		for (int i = 0; i < sumOfBoth.size(); i++) {
			String[] eachItem = sumOfBoth.get(i);
			String num1 = eachItem[0];
			int myNum1 = Integer.parseInt(num1);
			String num2 = eachItem[1];
			int myNum2 = Integer.parseInt(num2);
			String expected = eachItem[2];
			int myexpected = Integer.parseInt(expected);

			System.out.println("Adding " + num1 + " to " + num2 + " result is " + expected);

			if (myNum1 + myNum2 == myexpected) {
				System.out.println("Passed");
			} else {
				System.out.println("Failed : the actual result is " + num1 + num2);

				Assert.assertEquals(wholeSum, true);

			}
		}
	}
}
