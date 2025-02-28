package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;

public class ContactLookupPopupPage {
	
	ExcelFileUtility eutil = new ExcelFileUtility();
	JavaUtility jutil = new JavaUtility();
	
	WebDriver driver;
	public ContactLookupPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchTextField;
	
	@FindBy(name = "search")
	private WebElement searchButton;
	
	public WebElement getSearchTextField() {
		return searchTextField;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	
}
