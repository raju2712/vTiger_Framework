package Listener;

import org.testng.ITestResult;

public class RetryListener {
	
	public boolean retry(ITestResult result) {

	int count = 0;
	int limitCount = 5;
		if(count < limitCount) {
			count++;
			return true;
		}
		return false;
	}
}
