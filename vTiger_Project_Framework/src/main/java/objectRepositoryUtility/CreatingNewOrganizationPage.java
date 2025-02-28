package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.ExcelFileUtility;
import genericUtilities.WebDriverUtility;

public class CreatingNewOrganizationPage {
	
	public WebDriverUtility getWutil() {
		return wutil;
	}

	public WebElement getPhoneTextField() {
		return phoneTextField;
	}

	WebDriverUtility wutil = new WebDriverUtility();
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement accountnameTextField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	
			@FindBy(id = "phone")
			private WebElement phoneTextField;
			
	
	
	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getAccountnameTextField() {
		return accountnameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void toSelectIndustry() {
		wutil.toHandleDropDown(industryDropDown, "Energy");
	}
	
	public void toSelectType() {
		wutil.toHandleDropDown(typeDropDown, "Press");
	}
}
