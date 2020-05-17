import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class datePicker {

	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "E:\\Software\\seleniumWebdrivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();
			// driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.get("https://www.google.com/");
			// explicit wait
			WebDriverWait wait = new WebDriverWait(driver, 120);

			// Enter text to search on google page.
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("google booking flights");

			driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			// Wait for search result show up.
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//h3[contains(text(),'Flights - Google')]"))));

			// Click on the shown search result.
			driver.findElement(By.xpath("//h3[contains(text(),'Flights - Google')]")).click();

			// choose trip
			driver.findElement(By.xpath(
					"//div[@class='gws-flights-form__secondary-constraints gws-flights__flex-box']//div//dropdown-menu//span[@class='z1asCe K1bG5d']//*[local-name()='svg']"))
					.click();
			Thread.sleep(2000);

			driver.findElement(By.xpath(
					"//div[@class='mSPnZKpnf91__menu mSPnZKpnf91__cover-button mSPnZKpnf91__open']//span[contains(text(),'One way')]"))
					.click();
			Thread.sleep(2000);

			// Choose where to go.
			driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M12 2C8.13')]")).click();
			Thread.sleep(2000);

			WebElement goWhere = driver.findElement(By.xpath("//input[@placeholder='Where to?']"));
			goWhere.sendKeys("Las Vegas, Nevada");
			Thread.sleep(2000);
			goWhere.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			// Open Calander
			driver.findElement(By.xpath(
					"//div[@class='gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target']"))
					.click();
			Thread.sleep(2000);

			// Choose date
			driver.findElement(By.xpath("//calendar-month[2]//calendar-week[5]//calendar-day[3]//div[3]")).click();
			Thread.sleep(2000);

			// Close calander
			driver.findElement(By.xpath("//div[@class='eE8hUfzg9Na__overlay']")).click();

			Thread.sleep(2000);
			String test = driver.findElement(By.xpath(
					"//div[@class='gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target']"))
					.getText();

			String sExpected = "Tue, Jun 30";
			if (test.equals(sExpected)) {
				System.out.println("Date picked as expected: " + sExpected);
			} else {
				System.out.println("Date picked not as expected: " + test);
			}
			driver.close();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
