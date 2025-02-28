package ExtentReport;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestNG.baseClass.BaseClass;

public class ExtentReportWithScreenShotTest extends BaseClass {

	@Test
	public void CreateContact() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		
		test = report.createTest("CreateContact");

		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact");
		test.log(Status.INFO, "Create Contact");
		if ("ICICII".equals("ICICI")) {
			test.log(Status.PASS, "Contact Created");
		} else {
        test.addScreenCaptureFromBase64String(temp,"Errorfile");
        }
		driver.close();
	}

}
