package Mock;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class cliniqueTest {

	public static void main(String[] args) {
		
		

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.clinique.in/");
		WebElement skinCare = driver.findElement(By.partialLinkText("Skincare"));
		Actions a = new Actions(driver);
		a.moveToElement(skinCare).perform();
		
		List<WebElement> NavEle = driver.findElements(By.xpath("(//div[@class='gnav-desktop-sub-nav-content-column space-y-4'])[9]"));
		for (WebElement Nav : NavEle) {
			System.out.println(Nav.getText());
		}
		
	}
}
