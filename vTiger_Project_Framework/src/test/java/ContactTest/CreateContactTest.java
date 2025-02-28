package ContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read data from Excel file
		String LASTNAME = eutil.toReadDataFromExcel("contact",1,2).toString();


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

		wutil.toMaximize(driver);
		wutil.toWaitTillElementGetLoad(driver);


		//Step 2 : Login to application with valid credentials
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);

		//Step 3 : Navigate to contacts link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		//Step 4 : Click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();

		//Step 5 : Create contacts with mandatory fields
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(LASTNAME);
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

		//Step 7 : Logout of the Application
		hp.toSignout();

		//Step 8 : Close the browser
		driver.quit();
	}



}
