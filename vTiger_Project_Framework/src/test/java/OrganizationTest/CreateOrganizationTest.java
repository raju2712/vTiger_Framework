package OrganizationTest;

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


public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To read data from Property file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		System.out.println(BROWSER);
		String URL      = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read data from Excel file
		String ORGNAME  = eutil.toReadDataFromExcel("org", 1, 2)+ jutil.togetRandomNumber();

		// AutomationScript
		//Step 1 : Launch Browser(Polymorphism) and Maximize
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

		//Step 3 : Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		//Step 4 : Click on create organization lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		//Step 5 : Create Organization with mandetory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		//Step 6 : Save and verify the org name
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname +" ---Passed");
		}else {
			System.out.println(orgname +" ---Failed");
		}

		//Step 7 : Logout of the Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);
		driver.findElement(By.linkText("Sign Out")).click();

		//Step 8 : Close the browser
		driver.quit();
	} 

}
