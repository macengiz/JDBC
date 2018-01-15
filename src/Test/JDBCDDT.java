package Test;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdbcconnection.DBUtility;
import jdbcconnection.DBUtility.DBType;

public class JDBCDDT {

	WebDriver driver;

	@BeforeTest

	public void gotoApplication() {

		System.setProperty("webdriver.chrome.driver", "C:/Users/aydin/Documents/Libraries/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get("https://editor.datatables.net/examples/simple/simple.html");
	}

	@Test

	public void test() throws SQLException, InterruptedException {

		// connect to database
		DBUtility.establishConnection(DBType.MYSQL);
		// Run SQL query and store into a list
		List<String[]> queryResultList = DBUtility
				.runSQLQuery("SELECT first_name,last_name, job_id, city,phone_number, hire_date,salary "
						+ "FROM employees e JOIN departments d " + "ON e.department_id=d.department_id "
						+ "JOIN locations l " + "ON d.location_id=l.location_id");

		// close all connections
		DBUtility.closeConnections();
		// print all data in queryResultList

		// print value: first row and first cell data
		// String[] firstRow = queryResultList.get(0);
		// System.out.println(firstRow[0] + " works as " + firstRow[1] + ". His
		// salary is $" + firstRow[2]);

		WebDriverWait wait = new WebDriverWait(driver, 10);

		for (int rowNum = 0; rowNum < queryResultList.size(); rowNum++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("New")));
			driver.findElement(By.linkText("New")).click();
			// get data from SQL return list
			String[] rowData = queryResultList.get(rowNum);
			String firstName = rowData[0];
			String lastName = rowData[1];
			String position = rowData[2];
			String office = rowData[3];
			String extension = rowData[4].substring(8);
			String startDate = rowData[5];
			String salary = rowData[6];
			// enter data into front end
			driver.findElement(By.id("DTE_Field_first_name")).sendKeys(firstName);
			driver.findElement(By.id("DTE_Field_last_name")).sendKeys(lastName);
			driver.findElement(By.id("DTE_Field_position")).sendKeys(position);
			driver.findElement(By.id("DTE_Field_office")).sendKeys(office);
			driver.findElement(By.id("DTE_Field_extn")).sendKeys(extension);
			driver.findElement(By.id("DTE_Field_start_date")).sendKeys(startDate);
			driver.findElement(By.id("DTE_Field_salary")).sendKeys(salary);
			WebElement create = driver.findElement(By.xpath("//button[.='Create']"));
			create.click();

			wait.until(ExpectedConditions.invisibilityOf(create));
			// Thread.sleep(1000);

		}
	}
}
