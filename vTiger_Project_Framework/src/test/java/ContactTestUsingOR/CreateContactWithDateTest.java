package ContactTestUsingOR;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class CreateContactWithDateTest {

	@Test
	//public static void main(String[] args) throws Throwable {
	public void createContactWithDateTest() throws Throwable {

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

		//Start date and End Date
		String startDate = jutil.togetSystemDateYYYYMMDD();
		String endDate = jutil.togetRequiredDate(30);

		cncp.getStartDateTextField().clear();
		cncp.getStartDateTextField().sendKeys(startDate);

		cncp.getEndDateTextField().clear();
		cncp.getEndDateTextField().sendKeys(endDate);
		
		//Click on save
		cncp.getSaveButton().click();

		ContactInfoPage cip = new ContactInfoPage(driver);

		String lastnameinHeader = cip.getHeader().getText();
		if (lastnameinHeader.contains(LASTNAME)) {
			System.out.println(lastnameinHeader +" ---Passed");
		}else {
			System.out.println(lastnameinHeader +" ---Failed");
		}

		//To verify lastname TF
		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have"+LastnameTF +" ---Passed");
		}else {
			System.out.println("Lastname TF have"+LastnameTF +" ---Failed");
		}

		//To Start verify date
		String startDateTF = cip.getStartDateTextField().getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have"+startDateTF +" ---Passed");
		}else {
			System.out.println("start Date TF have"+startDateTF +" ---Failed");
		}

		//To End verify date
		String endDateTF = cip.getEndDateTextField().getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have "+endDateTF +" ---Passed");
		}else {
			System.out.println("End Date TF have "+endDateTF +" ---Failed");
		}

		//Step 7 : Logout of the Application
		hp.toSignout();

		//Step 8 : Close the browser
		driver.quit();
	}

}
