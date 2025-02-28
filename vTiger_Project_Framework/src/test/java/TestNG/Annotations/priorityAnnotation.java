package TestNG.Annotations;

import org.testng.annotations.Test;

public class priorityAnnotation {
	

	/**
	 * testNg executes in Alphabetical order
	 * so, to overcome this we use PRIORITY annotation
	 * In this TestNG class 3 Test classes are there
	 * 1.creating contact                      -------------------Executes 1st
	 * 2.creating contact with organization    -------------------Executes 3rd
	 * 3.creating organization                 -------------------Executes 2nd
	 * 
	 * Here, without creating Organization we can't able to create contact with organization
	 * If the previous TestCase failed also next TestCase will execute
	 */
	
	@Test(priority = 1)
	public void createContact() {
		System.out.println("Contact Created");
	}
	
	@Test(priority = 3)
	public void createContactWithOrganization() {
		System.out.println("Contact Created with Organization");
	}
	
	@Test(priority = 2)
	public void createOrganization() {
		System.out.println("Organization Created");
	}
}
