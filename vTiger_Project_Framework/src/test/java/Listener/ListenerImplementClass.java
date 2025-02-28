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

public class ListenerImplementClass implements ITestListener , ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("report configuration");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("-----------"+result.getMethod().getMethodName()+"-----START-------");

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
