package Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.ListenerImplementClass.class)
public class InvoiceTest2 extends Listener.BaseClassForListener {

	
	@Test
	public void createContact() {
		System.out.println("Execute createContact");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Home");
		System.out.println("Step-1A");
		System.out.println("Step-2A");
		System.out.println("Step-3A");
		System.out.println("Step-4A");
	}
	
	@Test
	public void createInvoiceWithContact() {
		System.out.println("Execute createInvoiceWithContact");
		System.out.println("Step-1B");
		System.out.println("Step-2B");
		System.out.println("Step-3B");
		System.out.println("Step-4B");
	}
	
	
}
