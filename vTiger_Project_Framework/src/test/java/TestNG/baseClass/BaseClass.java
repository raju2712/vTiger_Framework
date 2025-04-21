package TestNG.baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class BaseClass {

	public PropertyFileUtility putil = new PropertyFileUtility();
	public ExcelFileUtility eutil = new ExcelFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public JavaUtility jutil = new JavaUtility();

	protected WebDriver driver = null;

	public ExtentSparkReporter esr;
	public ExtentReports report;
	public ExtentTest test;

	@BeforeSuite
	public void ExtentReport() {
		esr = new ExtentSparkReporter("./AdvanceReport/ExtentReport.html");
		esr.config().setDocumentTitle("CRM Test Suite Result");
		esr.config().setReportName("CRM Report");
		esr.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Version", "133.0.6943.142 (Official Build) (64-bit)");
	}

//(groups = {"RT" , "ST"})
	@BeforeClass
	public void toLaunchBrowser() throws Throwable {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");

		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}

		wutil.toMaximize(driver);
		wutil.toWaitTillElementGetLoad(driver);
	}

	@BeforeMethod
	public void toLogin() throws Throwable {
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
	}

	@AfterMethod
	public void toLogout() {
		HomePage hp = new HomePage(driver);
		hp.toSignout();
	}

	@AfterClass
	public void toCloseBrowser() {
		driver.quit();
	}

	@AfterSuite
	public void ExtentReportFlush() {
		report.flush();
	}
}
