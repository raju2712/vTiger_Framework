package Listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplementationClass implements IRetryAnalyzer {

	int count = 0;
	int limitCount = 5;
	public boolean retry(ITestResult result) {
		return false;
	}

	
}
