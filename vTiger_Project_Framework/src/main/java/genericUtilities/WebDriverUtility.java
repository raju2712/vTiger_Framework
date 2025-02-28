package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	/**
	 * This method used to maximize
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method used to minimize
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method used to wait till the elements gets loaded(Implicit Wait)
	 * 
	 * @param driver
	 */
	public void toWaitTillElementGetLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	/**
	 * This method is used to wait till the element gets clickable provided driver
	 * and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitTillElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to wait till the element get visible provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitTillElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to Switch the Driver control to CHILD WINDOW
	 * 
	 * @param driver
	 * @param partialUrl
	 */
	public void switchToTabOnUrl(WebDriver driver, CharSequence partialUrl) {
		Set<String> allWindowIds = driver.getWindowHandles();
		Iterator<String> iterator = allWindowIds.iterator();
		
		while(iterator.hasNext()) {
			String ChildwindowId = iterator.next();
			driver.switchTo().window(ChildwindowId);
			
			String actualUrl = driver.getCurrentUrl();
			if(actualUrl.contains(partialUrl)) {
				break;
			}
		}
	}
	
	/**
	 * This method is used to Switch the Driver control back to PARENT WINDOW
	 * 
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToTabOnTitle(WebDriver driver, CharSequence partialTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		Iterator<String> iterator = allWindowIds.iterator();
		
		while(iterator.hasNext()) {
			String ChildwindowId = iterator.next();
			driver.switchTo().window(ChildwindowId);
			
			String actualUrl = driver.getTitle();
			if(actualUrl.contains(partialTitle)) {
				break;
			}
		}
	}

	/**
	 * This method is used to handle dropdown by using INDEX
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle dropdown by using VALUE
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle dropdown by using VISIBLE TEXT
	 * 
	 * @param element
	 * @param text
	 */
	public void toHandleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to handle the frame by INDEX
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to handle the frame by NAME_ID
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * This method is used to handle the frame by ELEMENT
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to SWITCH BACK the driver from frame
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to MOUSEOVER on the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to DOUBLE CLICK on the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDobleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to RIGHT CLICK on the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to CLICK AND HOLD the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toClickAndHold(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.clickAndHold(element).perform();
	}

	/**
	 * This method is used to RELEASE the hold element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRelease(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.release(element).perform();
	}
	/**
	 * This method is used to handle ALERT POPUP by ACCEPT
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to handle ALERT POPUP by DISMISS
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method is used to CAPTURE POPUP MESSAGE in popup
	 * @param driver
	 * @return
	 */
	public String toHandlePopupAndCaptureText(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String popupMsg = alertPopup.getText();
		alertPopup.accept();
		return popupMsg;
	}
	/**
	 * This method is used to take SCREENSHOT of ENTIRE WEBPAGE
	 * @param driver
	 * @param screenshotname
	 * @throws IOException 
	 */
	public void toTakeScreenShot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/"+screenshotname+".jpeg");
	    org.openqa.selenium.io.FileHandler.copy(temp, src);
	}
	/**
	 * This method is used to take SCREENSHOT of an ELEMENT
	 * @param driver
	 * @param xpathOfAnElement
	 * @throws IOException 
	 */
	public void toTakeScreenshot(WebDriver driver, String xpathOfAnElement) throws IOException {
		File temp = driver.findElement(By.xpath(xpathOfAnElement)).getScreenshotAs(OutputType.FILE);
		File src = new File("./ErrorShots/WoodenStreetLogo.jpeg");
		org.openqa.selenium.io.FileHandler.copy(temp, src);
	}
	
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		String[] allIds = null;
		for(String id : allIds) {
			String windowTitle = driver.switchTo().window(id).getTitle();
			if(windowTitle.contains(partialTitle)) {
				break;
			}
		}
	}
}
