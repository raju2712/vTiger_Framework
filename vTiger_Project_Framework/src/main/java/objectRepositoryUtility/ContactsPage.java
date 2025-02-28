package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	WebDriver driver;
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement contactLookupImage;

	



	public WebDriver getDriver() {
		return driver;
	}



	public WebElement getContactLookupImage() {
		return contactLookupImage;
	}



	public WebElement getSaveButton() {
		return saveButton;
	}

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;





}
