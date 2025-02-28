package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement header;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement organizationTextField;


	@FindBy(id = "mouseArea_Industry")
	private WebElement industeryTextField;


	@FindBy(id = "mouseArea_Type")
	private WebElement typeTextField;
	
	
			@FindBy(id = "mouseArea_Phone")
			private WebElement phoneTextField;
			


	public WebElement getPhoneTextField() {
				return phoneTextField;
			}


	public WebElement getOrganizationTextField() {
		return organizationTextField;
	}


	public WebElement getIndusteryTextField() {
		return industeryTextField;
	}


	public WebElement getTypeTextField() {
		return typeTextField;
	}


	public WebElement getHeader() {
		return header;
	}


}
