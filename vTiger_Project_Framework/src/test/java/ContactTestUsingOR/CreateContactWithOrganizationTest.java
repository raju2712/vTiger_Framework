package ContactTestUsingOR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactLookupPopupPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;

public class CreateContactWithOrganizationTest {

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
		String LASTNAME = eutil.toReadDataFromExcel("contact", 7, 3);

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

		//Step 6 : Save and verify the org name
		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getHeader().getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname +" ---Passed");
		}else {
			System.out.println(orgname +" ---Failed");
		}

		////Step 7 : Navigate to contact link
		//Creation of CONTACT			
		hp.getContactsLink().click();

		//Step 8 : Click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();

		//Step 9 : Enter LASTNAME
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(LASTNAME);

		/*	Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String startDate = sdf.format(date);

		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sdf.format(cal.getTime()); */

		//Start date and End Date
		String startDate = jutil.togetSystemDateYYYYMMDD();
		String endDate = jutil.togetRequiredDate(30);

		cncp.getStartDateTextField().clear();
		cncp.getStartDateTextField().sendKeys(startDate);

		cncp.getEndDateTextField().clear();
		cncp.getEndDateTextField().sendKeys(endDate);

		//Step 11 : Click on '+'symbol present beside organization textfield
		cncp.getOrganizationLookupImage().click();

		//Step 12 : Switch to Child Window
		wutil.switchToTabOnUrl(driver, "module=Accounts");


		//Step 13 : Enter ORG name and click search then click on unique org name link
		ContactLookupPopupPage clpp = new ContactLookupPopupPage(driver); 
		clpp.getSearchTextField().sendKeys(ORGNAME);
		clpp.getSearchButton().click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();

		//Step 13 : Switch to Parent Window
		wutil.switchToTabOnUrl(driver, "module=Contacts");

		//Step 14 : Click on Save
		cncp.getSaveButton().click();

		//Step 15 : verify Org name in TF
		ContactInfoPage cip = new ContactInfoPage(driver);
		String orgNameTF = cip.getOrganizationTextField().getText();
		if (orgNameTF.contains(ORGNAME)) {
			System.out.println("Org textfield have"+orgNameTF +" ---Passed");
		}else {
			System.out.println("Org textfield not have"+orgNameTF +" ---Failed");
		}

		//Step 16 : verify header
		String lastname = cip.getHeader().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname +" ---Passed");
		}else {
			System.out.println(lastname +" ---Failed");
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
