package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {

	public WebElement getOrganizationLookupImage() {
		return organizationLookupImage;
	}

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastNameTextField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement startDateTextField;
	
	@FindBy(id = "jscal_field_support_end_date")
	private WebElement endDateTextField;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement contactLookupImage;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement organizationLookupImage;
	
	
	
	public WebElement getContactLookupImage() {
		return contactLookupImage;
	}

	public WebElement getStartDateTextField() {
		return startDateTextField;
	}

	public WebElement getEndDateTextField() {
		return endDateTextField;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
}
