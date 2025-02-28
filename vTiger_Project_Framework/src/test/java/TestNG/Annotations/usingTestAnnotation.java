package TestNG.Annotations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;

public class usingTestAnnotation {
	
	/**
	 * This class contains 2 test cases--------------------2 @Test annotations are there
	 * Return type should always be void
	 * 
	 * @throws Throwable
	 */

	@Test
	public void createContactTest() throws Throwable {
		//public static void main(String[] args) throws Throwable {

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
		
		Reporter.log("Browser Opened");

		wutil.toMaximize(driver);
		wutil.toWaitTillElementGetLoad(driver);


		//Step 2 : Login to application with valid credentials
		driver.get(URL);
		
		Reporter.log("URL Entered");

		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		
		Reporter.log("LoggedIn Successfully");

		//Step 3 : Navigate to contacts link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		
		Reporter.log("Clicked on Contacts link");

		//Step 4 : Click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();
		
		Reporter.log("Clicked on Contacts LookUp Image");

		//Step 5 : Create contacts with mandatory fields
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(LASTNAME);
		
		Reporter.log("Lastname Entered");

		//Step 6 : Save and verify
		cncp.getSaveButton().click();
		
		Reporter.log("Saved Successfully");
		Reporter.log("Contact Info Page Opened");

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

		//Step 7 : Logout of the Application
		hp.toSignout();

		//Step 8 : Close the browser
		driver.quit();
	}


	@Test
	public void createOrganizationTest() throws Throwable {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To read data from Property file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
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

		//Step 2 : Enter url and Login to application with valid credentials
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);

		//		lp.getUserNameTextField().sendKeys(USERNAME);
		//		lp.getPasswordTextField().sendKeys(PASSWORD);
		//		lp.getLoginButton().click();

		lp.LoginToApp(USERNAME, PASSWORD);

		//Step 3 : Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		//Step 4 : Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		//Step 5 : Create Organization with mandetory fields
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getAccountnameTextField().sendKeys(ORGNAME);

		//Step 6 : Save and verify the org name
		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getHeader().getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname +" ---Passed");
		}else {
			System.out.println(orgname +" ---Failed");
		}

		//Navigate to campaigns page
		hp.gavigateToCampaignPage();

		//Step 7 : Logout of the Application
		hp.toSignout();

		//Step 8 : Close the browser
		driver.quit();
	}
}
