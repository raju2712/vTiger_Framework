package Listener;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericUtilities.UtilityClassObject;

public class ListenerImplementClassToAttachSSinExtentReportTest implements ITestListener , ISuiteListener{

	public ExtentSparkReporter esr;
	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		esr = new ExtentSparkReporter("./AdvanceReport/ExtentReport_"+time+".html");
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
		report.flush();                        //------------this will the report
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("-----------"+result.getMethod().getMethodName()+"-----START-------");
		test = report.createTest("-----------"+result.getMethod().getMethodName()+"-----------");
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+" is Stateted");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("-----------"+result.getMethod().getMethodName()+"-----END-------");
		test.log(Status.INFO, result.getMethod().getMethodName()+" is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot) (BaseClassForListener.edriver);
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		//File src = new File("./ErrorShots/"+testName+"_"+time+".jpeg");
        test.addScreenCaptureFromBase64String(temp,testName+"_"+time);

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.INFO, result.getMethod().getMethodName()+" is Skipped");

		
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
