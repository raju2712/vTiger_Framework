package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	       
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement header;

	@FindBy(id = "mouseArea_Last Name")
	private WebElement lastNameTextField;

	@FindBy(id = "mouseArea_Support Start Date")
	private WebElement startDateTextField;

	@FindBy(id = "mouseArea_Support End Date")
	private WebElement endDateTextField;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement organizationTextField;

	public WebElement getStartDateTextField() {
		return startDateTextField;
	}

	public WebElement getEndDateTextField() {
		return endDateTextField;
	}

	public WebElement getHeader() {
		return header;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getOrganizationTextField() {
		return organizationTextField;
	}
}
