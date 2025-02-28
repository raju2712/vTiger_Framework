package TestNG.Annotations;

import org.testng.annotations.Test;

public class dependsOnMethodAnnotation {

	/**
	 * It is like Priority annotation only.......the difference is, If the previous TestCase Failed next TestCase will also get Failed
	 * If pass Execute or else Skip
	 */
	
	@Test                                              
	public void createContact() {               //If i try to execute this TestCase only, it  will  execute this TestCases only because it 
		System.out.println("Contact Created");  //is not depends on any other Testcases
	}
	
	@Test(dependsOnMethods  = "createOrganization")     
	public void createContactWithOrganization() {                 //If i try to execute this TestCase only, it  will execute all the TestCases
		System.out.println("Contact Created with Organization");  //because itdepends on other Testcases
	}
	
	@Test(dependsOnMethods  = "createContact")   
	public void createOrganization() {
		System.out.println("Organization Created");
	}
}
