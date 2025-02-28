package Listener;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericUtilities.UtilityClassObject;

public class ListenerImplementClassForExtentReportTest implements ITestListener , ISuiteListener{

	public ExtentSparkReporter esr;
	public static ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report configuration");
		
		esr = new ExtentSparkReporter("./AdvanceReport/ExtentReport.html");
		esr.config().setDocumentTitle("CRM Test Suite Result");
		esr.config().setReportName("CRM Report");
		esr.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("Browser", "Chrome-3.2");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("-----------"+result.getMethod().getMethodName()+"-----START-------");
		
		test = report.createTest(result.getName());
		
		UtilityClassObject.setTest(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("-----------"+result.getMethod().getMethodName()+"-----END-------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) (BaseClassForListener.edriver);
		File temp = ts.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		File src = new File("./ErrorShots/"+testName+"_"+time+".jpeg");
		try {
			FileHandler.copy(temp, src);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	
}
