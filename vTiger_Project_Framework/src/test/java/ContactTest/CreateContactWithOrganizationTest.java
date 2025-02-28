package ContactTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactLookupPopupPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class CreateContactWithOrganizationTest {

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

		//Step 11 : Click on '+'symbol present beside organization textfield
		cncp.getContactLookupImage().click();

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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 15 : verify Org name in TF
		String orgNameTF = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (orgNameTF.contains(ORGNAME)) {
			System.out.println("Org textfield have"+orgNameTF +" ---Passed");
		}else {
			System.out.println("Org textfield not have"+orgNameTF +" ---Failed");
		}
		
		//Step 16 : verify header
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
		Actions a = new Actions(driver);
		a.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//Step 8 : Close the browser
		driver.quit();	
	}

}
