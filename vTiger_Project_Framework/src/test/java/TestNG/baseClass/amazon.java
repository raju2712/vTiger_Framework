package TestNG.baseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class amazon {
	
	@Test(dataProvider = "getData")
	public void Amazon(String brandName, String productName) throws Throwable {
		
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		
		WebDriver driver = null;
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}		
		
		driver.get("https://www.amazon.in/");
		wutil.toMaximize(driver);
		wutil.toWaitTillElementGetLoad(driver);
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		String x = "//span[normalize-space()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		WebElement price = driver.findElement(By.xpath(x));
		System.out.println(price);
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable, Throwable {
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		int rowCount = eutil.getRowCount("product");
		Object[][] objArr = new Object[rowCount][2];  
		
     for(int i=0; i<rowCount;i++) {                                   		
		objArr[i][0] = eutil.toReadDataFromExcel("product", i+1, 0);          
		objArr[i][1] = eutil.toReadDataFromExcel("product", i+1, 1);
       }
		return objArr;
		
	}
}
