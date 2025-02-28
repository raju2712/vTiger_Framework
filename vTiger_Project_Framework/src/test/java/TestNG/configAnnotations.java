package TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class configAnnotations {

	
	@BeforeClass
	public void Beforelass() {
		System.out.println("Before Class");
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		System.out.println("Before Method");
	}
	
	@Test
	public void CreateContact() {
		System.out.println("Create Contact");
	}
	
	@Test
	public void CreateContactWithSupportDate() {
		System.out.println("Create Contact With Support Date");
	}
	
	@AfterMethod
	public void AfterMethod() {
		System.out.println("After Method");
	}
	
	@AfterClass
	public void AfterClass() {
		System.out.println("After Class");
	}
	
}
