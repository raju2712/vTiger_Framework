package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProviderAnnotation {
	
	/**
	 * data provider annotation used to execute the same testcase multiple times with different set of Data
	 */
	@Test(dataProvider = "getData")
	public void createContact(String firstName, String lastName) {
		System.out.println("Firstname is " + firstName + " / Lastname is " + lastName);
	}
	
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		//Here 3 indicates How times want to execute and 2 indicates
		objArr[0][0] = "Raju";                //How many arguments we have to pass
		objArr[0][1] = "Ramesh";
		
		objArr[1][0] = "Appu";
		objArr[1][1] = "Vanitha";
		
		objArr[2][0] = "Kasi";
		objArr[2][1] = "Dhanam";
		
		return objArr;
		
	}
	
}
