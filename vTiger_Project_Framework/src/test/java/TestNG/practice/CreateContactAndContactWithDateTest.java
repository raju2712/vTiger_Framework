package TestNG.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactLookupPopupPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.CreatingNewOrganizationPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.OrganizationInfoPage;
import objectRepositoryUtility.OrganizationsPage;

public class CreateContactAndContactWithDateTest extends BaseClass {

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

		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have" + LastnameTF + " ---Passed");
		} else {
			System.out.println("Lastname TF have" + LastnameTF + " ---Failed");
		}

		String startDateTF = cip.getStartDateTextField().getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have" + startDateTF + " ---Passed");
		} else {
			System.out.println("start Date TF have" + startDateTF + " ---Failed");
		}

		String endDateTF = cip.getEndDateTextField().getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have " + endDateTF + " ---Passed");
		} else {
			System.out.println("End Date TF have " + endDateTF + " ---Failed");
		}
	}

	@Test
	public void CreateContactWithOrganizationTest() throws Throwable, IOException {

		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getorganizationLookupImage().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		String ORGNAME = eutil.toReadDataFromExcel("contact", 7, 2) + jutil.togetRandomNumber();
		cnop.getAccountnameTextField().sendKeys(ORGNAME);

		cnop.getSaveButton().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getHeader().getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println(orgname + " ---Passed");
		} else {
			System.out.println(orgname + " ---Failed");
		}

		hp.getContactsLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookupImage().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		String LASTNAME = eutil.toReadDataFromExcel("contact", 7, 3);
		cncp.getLastNameTextField().sendKeys(LASTNAME);

		String startDate = jutil.togetSystemDateYYYYMMDD();
		String endDate = jutil.togetRequiredDate(30);

		cncp.getStartDateTextField().clear();
		cncp.getStartDateTextField().sendKeys(startDate);

		cncp.getEndDateTextField().clear();
		cncp.getEndDateTextField().sendKeys(endDate);

		cncp.getOrganizationLookupImage().click();

		wutil.switchToTabOnUrl(driver, "module=Accounts");

		ContactLookupPopupPage clpp = new ContactLookupPopupPage(driver);
		clpp.getSearchTextField().sendKeys(ORGNAME);
		clpp.getSearchButton().click();
		driver.findElement(By.xpath("//a[text()='" + ORGNAME + "']")).click();

		wutil.switchToTabOnUrl(driver, "module=Contacts");

		cncp.getSaveButton().click();

		ContactInfoPage cip = new ContactInfoPage(driver);
		String orgNameTF = cip.getOrganizationTextField().getText();
		if (orgNameTF.contains(ORGNAME)) {
			System.out.println("Org textfield have" + orgNameTF + " ---Passed");
		} else {
			System.out.println("Org textfield not have" + orgNameTF + " ---Failed");
		}

		String lastname = cip.getHeader().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + " ---Passed");
		} else {
			System.out.println(lastname + " ---Failed");
		}

		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have" + LastnameTF + " ---Passed");
		} else {
			System.out.println("Lastname TF have" + LastnameTF + " ---Failed");
		}

		String startDateTF = cip.getStartDateTextField().getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have" + startDateTF + " ---Passed");
		} else {
			System.out.println("start Date TF have" + startDateTF + " ---Failed");
		}

		String endDateTF = cip.getEndDateTextField().getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have " + endDateTF + " ---Passed");
		} else {
			System.out.println("End Date TF have " + endDateTF + " ---Failed");
		}

	}

}
