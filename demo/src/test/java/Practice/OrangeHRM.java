package Practice;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class OrangeHRM extends Base {
  @Test
  public void orangeHRM() {
    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    driver.get(url);

    /**
     * https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
     * Difference between Implicit, Explicit wait, Fluent Wait
     * Implicit - For each assertion
     * Explicit - Condition Based
     * Fluent - Time Interval
     */

    String username = "Admin";
    String password = "admin123";

    mywait = new WebDriverWait(driver, Duration.ofSeconds(30));
    mywait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//input[@class='oxd-input oxd-input--active' and @name='username']")));

    driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active' and @name='username']"))
        .sendKeys(username);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.findElement(By.xpath("//input[@class='oxd-input oxd-input--active' and @name='password']"))
        .sendKeys(password);

    driver.findElement(By.xpath(
        "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button' and @type='submit']"))
        .click();

    boolean isLoggedIn = false;
    try {
      String errorText = driver.findElement(By.xpath(
          "//div[@class='oxd-alert-content oxd-alert-content--error']/child::p")).getText();
      System.out.println(errorText);
    } catch (Exception e) {
      isLoggedIn = true;
    }

    if (isLoggedIn) {
      System.out.println("Login Successfull");
    }
  }

}
