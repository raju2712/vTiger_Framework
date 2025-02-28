package OrganizationTestUsingOR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;

public class CreateOrganizationTest {

	@Test
	public static void main(String[] args) throws Throwable {

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
