package OrganizationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws Throwable {

		FileInputStream pfis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);

		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		// To read data from Excel file
		FileInputStream efis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		
		Random r = new Random();
		int random = r.nextInt(1000);
		String ORGNAME = wb.getSheet("org").getRow(4).getCell(2).toString() + random;
		String INDNAME = wb.getSheet("org").getRow(4).getCell(3).toString();
		String TYPE = wb.getSheet("org").getRow(4).getCell(4).toString();
		

		// AutomationScript
		//Step 1 : Launch Browser(Polymorphism)
		WebDriver driver = null;
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		//Step 2 : Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step 3 : Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		//Step 4 : Click on create organization lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		//Step 5 : Create Organization with mandetory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 6 : Create Industry
		WebElement industryDD = driver.findElement(By.name("industry"));
		Select dp = new Select(industryDD);
		dp.selectByValue(INDNAME);
		
		WebElement TypeDD = driver.findElement(By.name("accounttype"));
		Select dp1 = new Select(TypeDD);
		dp1.selectByValue(TYPE);

		//Step 7 : Save and verify the org name
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (orgname.contains(ORGNAME)) {
			System.out.println("Header "+orgname +" ---Passed");
		}else {
			System.out.println("Header "+orgname +" ---Failed");
		}
		
		//To verify Org name in TF
		String TFOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (TFOrgname.contains(ORGNAME)) {
			System.out.println("Organization text field have "+TFOrgname+" ---Passed");
		}else {
			System.out.println("Organization text field not have "+TFOrgname+" ---Failed");
		}
		
		//To verify Ind
		String IndName = driver.findElement(By.id("mouseArea_Industry")).getText();
		if (IndName.contains(INDNAME)) {
			System.out.println(IndName +" ---Passed");
		}else {
			System.out.println(IndName +" ---Failed");
		}
		
		//To verify Type
		String type = driver.findElement(By.id("mouseArea_Type")).getText();
		if (type.contains(TYPE)) {
			System.out.println(type +" ---Passed");
		}else {
			System.out.println(type +" ---Failed");
		}

		//Step 8 : Logout of the Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//Step 9 : Close the browser
		driver.quit();
	}

}
