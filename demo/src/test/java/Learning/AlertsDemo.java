package learning;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class AlertsDemo extends Base {

    @Test
    public void AlertDemo1() throws InterruptedException {
        mywait = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wiat declaration
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click(); // opens alert box
        Thread.sleep(2000);
        Alert myalert = mywait.until(ExpectedConditions.alertIsPresent()); // capture alertbox using explicit wait
        myalert.accept();
    }

    @Test
    public void AlertDemo2() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // 1) Normal alert with OK button
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click(); // opens alert box
        // Thread.sleep(5000);

        // Method 1
        // Alert myalert = driver.switchTo().alert();
        // myalert.accept();
        // Method 1

        // Method 2
        driver.switchTo().alert().accept(); // Single line statement
        // Method 2

    }

    @Test
    public void AlertDemo3() throws InterruptedException {
        // 2) confirmation Alert - OK & Cancel
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click(); // opens alert
        Thread.sleep(5000);

        // driver.switchTo().alert().accept(); // close alert window using OK button
        driver.switchTo().alert().dismiss(); // close alert window using Cancel button

    }

    @Test
    public void AlertDemo4() throws InterruptedException {
        // 3) Prompt alert- Input box

        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click(); // opens alert
        Thread.sleep(5000);

        Alert myalert = driver.switchTo().alert();

        System.out.println("Text msg on alert:" + myalert.getText()); // I am a JS prompt

        myalert.sendKeys("John");
        myalert.accept();

        String res = driver.findElement(By.xpath("//p[@id='result']")).getText();

        if (res.contains("John")) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }
    }

    @Test
    public void AlertDemo5() throws InterruptedException {
        // driver.get("http://the-internet.herokuapp.com/basic_auth");

        // syntax
        // http://username:<password>@the-internet.herokuapp.com/basic_auth

        driver.get("http://admin:abcde131@the-internet.herokuapp.com/basic_auth");
        // in the popup the username will get input as admin and password as abcde131

        driver.manage().window().maximize();
    }
}
