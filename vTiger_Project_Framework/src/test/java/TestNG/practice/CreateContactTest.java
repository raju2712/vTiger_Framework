package TestNG.practice;

import org.testng.annotations.Test;

import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.HomePage;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Throwable {

		String LASTNAME = eutil.toReadDataFromExcel("contact", 1, 2).toString();
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(LASTNAME);

		cncp.getSaveButton().click();

		ContactInfoPage cip = new ContactInfoPage(driver);

		String lastnameinHeader = cip.getHeader().getText();
		if (lastnameinHeader.contains(LASTNAME)) {
			System.out.println(lastnameinHeader + " ---Passed");
		} else {
			System.out.println(lastnameinHeader + " ---Failed");
		}

		// To verify lastname TF
		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have" + LastnameTF + " ---Passed");
		} else {
			System.out.println("Lastname TF have" + LastnameTF + " ---Failed");
		}
	}

	@Test
	public void createContactWithDate() throws Throwable {

		String LASTNAME = eutil.toReadDataFromExcel("contact", 1, 2).toString();
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(LASTNAME);

		// Start date and End Date
		String startDate = jutil.togetSystemDateYYYYMMDD();
		String endDate = jutil.togetRequiredDate(30);

		cncp.getStartDateTextField().clear();
		cncp.getStartDateTextField().sendKeys(startDate);

		cncp.getEndDateTextField().clear();
		cncp.getEndDateTextField().sendKeys(endDate);

		cncp.getSaveButton().click();

		ContactInfoPage cip = new ContactInfoPage(driver);

		String lastnameinHeader = cip.getHeader().getText();
		if (lastnameinHeader.contains(LASTNAME)) {
			System.out.println(lastnameinHeader + " ---Passed");
		} else {
			System.out.println(lastnameinHeader + " ---Failed");
		}

		// To verify lastname TF
		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have" + LastnameTF + " ---Passed");
		} else {
			System.out.println("Lastname TF have" + LastnameTF + " ---Failed");
		}

		// To Start verify date
		String startDateTF = cip.getStartDateTextField().getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have" + startDateTF + " ---Passed");
		} else {
			System.out.println("start Date TF have" + startDateTF + " ---Failed");
		}

		// To End verify date
		String endDateTF = cip.getEndDateTextField().getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have " + endDateTF + " ---Passed");
		} else {
			System.out.println("End Date TF have " + endDateTF + " ---Failed");
		}
	}

}
