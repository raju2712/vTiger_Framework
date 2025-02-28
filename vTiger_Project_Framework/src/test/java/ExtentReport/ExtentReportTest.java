package ExtentReport;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestNG.baseClass.BaseClass;

@Listeners(Listener.ListenerImplementClassForExtentReportTest.class)
public class ExtentReportTest extends BaseClass {

	@Test
	public void CreateContact() {

		test = report.createTest("CreateContact");

		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact");
		test.log(Status.INFO, "Create Contact");
		
		if ("ICICI".equals("ICICI")) {
			test.log(Status.PASS, "Contact Created");
		} else {
			test.log(Status.FAIL, "Contact not created");
		}
	}

	@Test
	public void CreateContactWithOrg() {

		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact");
		test.log(Status.INFO, "Create Contact with Org");
		if ("ICICI".equals("ICICI")) {
			test.log(Status.PASS, "Contact Created with Org");
		} else {
			test.log(Status.FAIL, "Contact not created with Org");
		}
	}

	@Test
	public void CreateContactWithPhone() {

		test = report.createTest("CreateContactWithPhone");

		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigate to contact");
		test.log(Status.INFO, "Create Contact with phone");
		if ("ICICI".equals("ICICI")) {
			test.log(Status.PASS, "Contact Created with Phone");
		} else {
			test.log(Status.FAIL, "Contact not created with Phone");
		}
	}
}
