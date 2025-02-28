package Listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	ExcelFileUtility eutil = new ExcelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	JavaUtility jutil = new JavaUtility();

	WebDriver driver = null;

	@BeforeClass(groups = {"RT" , "RT"})
	public void toLaunchBrowser() throws Throwable {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");

		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wutil.toMaximize(driver);
		wutil.toWaitTillElementGetLoad(driver);
	}

	@BeforeMethod(groups = {"RT" , "RT"})
	public void toLogin() throws Throwable {
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"RT" , "RT"})
	public void toLogout() {
		HomePage hp = new HomePage(driver);
		hp.toSignout();
	}
	
	@AfterClass(groups = {"RT" , "RT"})
	public void toCloseBrowser() {
		driver.quit();
	}
}
