package learning;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.Base;

public class FrameDemo extends Base {

    @Test
    public void frameDemo() {
        driver.get("https://ui.vision/demo/webtest/frames/");

        // Frame1
        WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
        driver.switchTo().frame(frame1);
        driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Welcome");

        driver.switchTo().defaultContent();

        // Frame2
        WebElement frame2 = driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
        driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("Automation");

        driver.switchTo().defaultContent();

        // frame3
        WebElement frame3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
        driver.switchTo().frame(frame3);
        driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("programming");

        // swith to inner frame (part of frame3)
        driver.switchTo().frame(0); // switched to inner frame

        driver.findElement(By.xpath("//div[@data-value='Hi, I am the UI.Vision IDE']")).click();
    }
}
