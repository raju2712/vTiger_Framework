package TestNG.practice;

import org.testng.annotations.Test;

import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass{

	@Test
	//public static void main(String[] args) throws Throwable {
	public void createOrganizationTest() throws Throwable {

		// To read data from Excel file
		String ORGNAME  = eutil.toReadDataFromExcel("org", 1, 2)+ jutil.togetRandomNumber();

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
			System.out.println(orgname +" ---Passed");
		}else {
			System.out.println(orgname +" ---Failed");
		}

		//Navigate to campaigns page
		hp.gavigateToCampaignPage();
	}
}
