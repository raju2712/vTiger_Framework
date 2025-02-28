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

public class CreateOrganizationWithIndustryTest {

	@Test
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
		String ORGNAME = eutil.toReadDataFromExcel("contact", 7, 2) + jutil.togetRandomNumber();
		String INDNAME =eutil.toReadDataFromExcel("org", 4, 3);
		String TYPE = eutil.toReadDataFromExcel("org", 4, 4);

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

		//Creation of ORGANIZATION		
		//Step 3 : Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		//Step 4 : Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		//Step 5 : Create Organization with mandetory fields
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getAccountnameTextField().sendKeys(ORGNAME);
		
		// To select Industry
		cnop.toSelectIndustry();
		
		//To select Type
		cnop.toSelectType();

		//Step 6 : Save and verify the org name
		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgnameInHeader = oip.getHeader().getText();
		if (orgnameInHeader.contains(ORGNAME)) {
			System.out.println(orgnameInHeader +" ---Passed");
		}else {
			System.out.println(orgnameInHeader +" ---Failed");
		}

		//To verify Org name in TF
		String TFOrgname = oip.getOrganizationTextField().getText();
		if (TFOrgname.contains(ORGNAME)) {
			System.out.println("Organization text field have "+TFOrgname+" ---Passed");
		}else {
			System.out.println("Organization text field not have "+TFOrgname+" ---Failed");
		}

		//To verify Ind
		String IndName = oip.getIndusteryTextField().getText();
		if (IndName.contains(INDNAME)) {
			System.out.println(IndName +" ---Passed");
		}else {
			System.out.println(IndName +" ---Failed");
		}

		//To verify Type
		String type = oip.getTypeTextField().getText();
		if (type.contains(TYPE)) {
			System.out.println(type +" ---Passed");
		}else {
			System.out.println(type +" ---Failed");
		}

		//Step 8 : Logout of the Application
		hp.toSignout();

		//Step 9 : Close the browser
		driver.quit();
	}
}
