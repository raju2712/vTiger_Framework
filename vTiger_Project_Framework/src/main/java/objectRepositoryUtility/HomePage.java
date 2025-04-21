package objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage {

	WebDriverUtility wutil = new WebDriverUtility();

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement profileIcon;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	public WebElement getSignoutLink() {
		return signoutLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getProfileIcon() {
		return profileIcon;
	}

	public void gavigateToCampaignPage() {
		wutil.toMouseHover(driver, moreLink);
		campaignsLink.click();
	}

	public void toSignout() {
		wutil.toMouseHover(driver, profileIcon);
		signoutLink.click();
	}

}
