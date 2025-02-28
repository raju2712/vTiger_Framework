package TestNG.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class CreateContactWithDateTest {

	public static void main(String[] args) throws Throwable {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		 
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcel("contact", 1, 2);

		// AutomationScript
		//Step 1 : Launch Browser(Polymorphism)
		WebDriver driver = null;
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wutil.toWaitTillElementGetLoad(driver);
		wutil.toMaximize(driver);

		//Step 2 : Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step 3 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		//Step 4 : Click on create contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Step 5 : Create contacts with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Start date and End Date
		String startDate = jutil.togetSystemDateYYYYMMDD();
		String endDate = jutil.togetRequiredDate(30);
		
		driver.findElement(By.id("jscal_field_support_start_date")).clear();
		driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);

		driver.findElement(By.id("jscal_field_support_end_date")).clear();
		driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
		System.out.println(endDate);

		//Step 6 : Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname +" ---Passed");
		}else {
			System.out.println(lastname +" ---Failed");
		}
		
		//To verify lastname TF
		String LastnameTF = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have"+LastnameTF +" ---Passed");
		}else {
			System.out.println("Lastname TF have"+LastnameTF +" ---Failed");
		}
		
		//To Start verify date
		String startDateTF = driver.findElement(By.id("mouseArea_Support Start Date")).getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have"+startDateTF +" ---Passed");
		}else {
			System.out.println("start Date TF have"+startDateTF +" ---Failed");
		}
		
		//To End verify date
		String endDateTF = driver.findElement(By.id("mouseArea_Support End Date")).getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have "+endDateTF +" ---Passed");
		}else {
			System.out.println("End Date TF have "+endDateTF +" ---Failed");
		}

		//Step 7 : Logout of the Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);
		driver.findElement(By.linkText("Sign Out")).click();

		//Step 8 : Close the browser
		driver.quit();
	}

}
