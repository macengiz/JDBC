package jdbcconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import jdbcconnection.DBUtility.DBType;

public class DBUtilTest {

	@Test

	public void testUtilityConnection() throws SQLException {

		Connection connection = DBUtility.getConnection(DBType.MYSQL);
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery("Select * from employees");

		List<String[]> sqlResultsList = new ArrayList<>();
		ResultSetMetaData rsMetaData = resultset.getMetaData();
		int columnsCount = rsMetaData.getColumnCount();
		String[] colNames = new String[columnsCount];

		for (int colIndex = 1; colIndex <= columnsCount; colIndex++) {
			colNames[colIndex - 1] = rsMetaData.getColumnName(colIndex);
		}
		sqlResultsList.add(colNames);

		while (resultset.next()) {
			String[] rowData = new String[columnsCount];
			for (int cellNum = 1; cellNum <= columnsCount; cellNum++) {
				rowData[cellNum - 1] = resultset.getString(cellNum);
			}
			sqlResultsList.add(rowData);
		}

		for (String[] rd : sqlResultsList) {
			for (String cellData : rd) {
				System.out.print(cellData + "--> ");
			}
			System.out.println();
		}
		resultset.close();
		statement.close();
		connection.close();

		chooseDay(WeekDays.Monday);
	}

	public void chooseDay(WeekDays days) {

		switch (days) {
		case Monday:
		case Tuesday:
		case Wednesday:
			System.out.println("first part of week");
			break;
		case Thursday:
		case Friday:
			System.out.println("almost weekend");
			break;
		case Saturday:
		case Sunday:

		}
	}

}
