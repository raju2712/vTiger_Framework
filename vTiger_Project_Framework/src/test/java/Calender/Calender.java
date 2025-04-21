package Calender;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Calender {
	
	@Test
	public void makeMyTripCalenderTest() throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://www.makemytrip.com/");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("chennai");
		driver.findElement(By.xpath("//span[text()='Chennai']")).click();
		
		driver.findElement(By.xpath("//label[@for='toCity']")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("goa");
		driver.findElement(By.xpath("//p[text()='Goa - Dabolim Airport, India']")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Departure')]")).click();
		
		for (;;) {
			try {
				driver.findElement(By.xpath("//div[@aria-label='Fri Aug 15 2025']//p[contains(text(),'15')]")).click();
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
		
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")).clear();
		
		
	}

}
