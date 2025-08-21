package learning;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import base.Base;

public class WaitsDemo extends Base {
	@Test
	public void FluentWaitDemo() {
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		Wait<WebDriver> myWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(20)) // Maximum 10 seconds of wait
				.pollingEvery(Duration.ofSeconds(4)) // Will check in every 2 seconds (5 times it will check)
				.ignoring(NoSuchElementException.class); // Within the above mentioned time lines the system will ignore
															// the exception [after 20 seconds it will through]

		driver.get(url);
		driver.manage().window().maximize();

		WebElement usernameInputBox = myWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
		// WebElement usernameInputBox = myWait.until(new Function<WebDriver,
		// WebElement>() {
		// @Override
		// public WebElement apply(WebDriver driver) {
		// WebElement txtUsername =
		// driver.findElement(By.xpath("//input[@placeholder='Username']"));
		// return txtUsername;
		// }
		// });

		String username = "Admin";
		// String password = "admin123";

		usernameInputBox.sendKeys(username);
	}

	@Test
	public void NavigateCommands() throws MalformedURLException {
		String url1 = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String url2 = "https://demo.nopcommerce.com/";
		driver.navigate().to(url1);
		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to(new URL(url2));

		Set<String> windowIds = driver.getWindowHandles();

		// Approach 1
		List<String> windowList = new ArrayList<String>(windowIds);
		String parentId = windowList.get(0);
		String childId = windowList.get(1);
		driver.switchTo().window(parentId);
		System.out.println(driver.getTitle());
		driver.switchTo().window(childId);
		System.out.println(driver.getTitle());

		// Approach 2
		for (String id : windowIds) {
			String title = driver.switchTo().window(id).getTitle();
			System.out.println(title);
			if (title.contains("nopCommerce")) {
				driver.close();
			}
		}
		driver.switchTo().window(windowList.get(0));
		System.out
				.println("After closing nopCommerce title - " + driver.getTitle());
	}

	@AfterEach
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
