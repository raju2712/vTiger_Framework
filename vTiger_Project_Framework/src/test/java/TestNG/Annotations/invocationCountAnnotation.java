package TestNG.Annotations;

import org.testng.annotations.Test;

public class invocationCountAnnotation {

	/**
	 * If we give Invocation count = 10 to any of the testcase it will execute that testcase for 10 times
	 */
	
	@Test(invocationCount = 6)
	public void createContact() {
		System.out.println("Contact Created");
	}
	
	@Test(enabled = false)  //enabled = false annotation used to skip the testcase,By default it is true
	public void createContactWithOrganization() {
		System.out.println("Contact Created with Organization");
	}
	
	@Test
	public void createOrganization() {
		System.out.println("Organization Created");
	}
}
