package Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.ListenerImplementClass.class)
public class RetryListener_InvoiceTest extends Listener.BaseClassForListener {

	/**
	 * Whenever test fails, retryAnalyser re-execute the same testCase for given number of times
	 */
	
	@Test(retryAnalyzer = Listener.RetryListenerImplementationClass.class)
	public void createContact() {
		System.out.println("Execute createContact");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Home");
		System.out.println("Step-1A");
		System.out.println("Step-2A");
		System.out.println("Step-3A");
		System.out.println("Step-4A");
	}
	
	
	
	
}
