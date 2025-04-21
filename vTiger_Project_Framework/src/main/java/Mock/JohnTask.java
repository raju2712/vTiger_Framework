package Mock;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.WebDriverUtility;

public class JohnTask {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).sendKeys("iphone",Keys.ENTER);
		String phone = driver.findElement(By.xpath("//div[contains(text(),'Apple iPhone 15')]")).getText();
        driver.findElement(By.xpath("(//div[text()='"+phone+"']/../../div[2]/div/div/div[1])[1]")).getText();
        
//		String iphone = driver.findElement(By.xpath("//div[contains(text(),Apple iPhone 15)']")).getText();
//		WebElement price = driver.findElement(By.xpath("(//div[text()='"+iphone+"']/../../div[2]/div/div/div[1])[1]"));
//		System.out.println(price.getText());
			
		}
		
		
	}


