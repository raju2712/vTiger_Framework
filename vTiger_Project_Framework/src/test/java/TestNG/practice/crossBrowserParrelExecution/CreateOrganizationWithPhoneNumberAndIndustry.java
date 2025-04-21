package TestNG.practice.crossBrowserParrelExecution;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestNG.baseClass.BaseClass;
import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;
@Parameters
public class CreateOrganizationWithPhoneNumberAndIndustry extends BaseClassForCrossBrowserParrallelExecution {

	@Test(groups = "RT")
	public void createOrganizationTest() throws Throwable {

		String ORGNAME = eutil.toReadDataFromExcel("org", 1, 2) + jutil.togetRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getAccountnameTextField().sendKeys(ORGNAME);

		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getHeader().getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname + " ---Passed");
		} else {
			System.out.println(orgname + " ---Failed");
		}

		hp.gavigateToCampaignPage();
	}

	@Test(groups = "ST")
	public void CreateOrganizationWithIndustry() throws Throwable, IOException {

		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// Step 4 : Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		// Step 5 : Create Organization with mandetory fields
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		String ORGNAME = eutil.toReadDataFromExcel("org", 1, 2) + jutil.togetRandomNumber();
		cnop.getAccountnameTextField().sendKeys(ORGNAME);

		cnop.toSelectIndustry();

		cnop.toSelectType();

		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgnameInHeader = oip.getHeader().getText();
		if (orgnameInHeader.contains(ORGNAME)) {
			System.out.println(orgnameInHeader + " ---Passed");
		} else {
			System.out.println(orgnameInHeader + " ---Failed");
		}

		String TFOrgname = oip.getOrganizationTextField().getText();
		if (TFOrgname.contains(ORGNAME)) {
			System.out.println("Organization text field have " + TFOrgname + " ---Passed");
		} else {
			System.out.println("Organization text field not have " + TFOrgname + " ---Failed");
		}

		String IndName = oip.getIndusteryTextField().getText();
		String INDNAME = eutil.toReadDataFromExcel("org", 4, 3);
		if (IndName.contains(INDNAME)) {
			System.out.println(IndName + " ---Passed");
		} else {
			System.out.println(IndName + " ---Failed");
		}

		// To verify Type
		String type = oip.getTypeTextField().getText();
		String TYPE = eutil.toReadDataFromExcel("org", 4, 4);
		if (type.contains(TYPE)) {
			System.out.println(type + " ---Passed");
		} else {
			System.out.println(type + " ---Failed");
		}
	}

	@Test(groups = "ST")
	public void CreateOrganizationWithPhoneNumber() throws Throwable, IOException {

		String ORGNAME = eutil.toReadDataFromExcel("org", 4, 2) + jutil.togetRandomNumber();
		String INDNAME = eutil.toReadDataFromExcel("org", 4, 3);
		String TYPE = eutil.toReadDataFromExcel("org", 4, 4);
		String PHONE = eutil.toReadDataFromExcel("org", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getAccountnameTextField().sendKeys(ORGNAME);

		cnop.toSelectIndustry();

		cnop.toSelectType();

		cnop.getPhoneTextField().sendKeys(PHONE);

		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgnameInHeader = oip.getHeader().getText();
		if (orgnameInHeader.contains(ORGNAME)) {
			System.out.println(orgnameInHeader + " ---Passed");
		} else {
			System.out.println(orgnameInHeader + " ---Failed");
		}

		String TFOrgname = oip.getOrganizationTextField().getText();
		if (TFOrgname.contains(ORGNAME)) {
			System.out.println("Organization text field have " + TFOrgname + " ---Passed");
		} else {
			System.out.println("Organization text field not have " + TFOrgname + " ---Failed");
		}

		String IndName = oip.getIndusteryTextField().getText();
		if (IndName.contains(INDNAME)) {
			System.out.println(IndName + " ---Passed");
		} else {
			System.out.println(IndName + " ---Failed");
		}

		String type = oip.getTypeTextField().getText();
		if (type.contains(TYPE)) {
			System.out.println(type + " ---Passed");
		} else {
			System.out.println(type + " ---Failed");
		}

		String phone = oip.getPhoneTextField().getText();
		if (phone.contains(PHONE)) {
			System.out.println(phone + " ---Passed");
		} else {
			System.out.println(phone + " ---Failed");
		}
	}
}
