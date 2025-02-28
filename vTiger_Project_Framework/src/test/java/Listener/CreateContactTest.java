package Listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestNG.baseClass.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositoryUtility.ContactInfoPage;
import objectRepositoryUtility.ContactsPage;
import objectRepositoryUtility.CreatingNewContactPage;
import objectRepositoryUtility.HomePage;
import objectRepositoryUtility.LoginPage;

public class CreateContactTest extends BaseClassForListener{

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
		Assert.assertEquals(lastnameinHeader, "ABCD");

		String LastnameTF = cip.getLastNameTextField().getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have" + LastnameTF + " ---Passed");
		} else {
			System.out.println("Lastname TF have" + LastnameTF + " ---Failed");
		}
	}



}
