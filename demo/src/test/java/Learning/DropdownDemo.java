package learning;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.Base;

public class DropdownDemo extends Base {
    @Test
    public void dropdownDemo1() {
        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

        driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click(); // opens dropdown options

        // 1) Select single option
        // driver.findElement(By.xpath("//input[@value='Java']")).click();

        // 2) capture all the options and find out size
        List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,'multiselect')]//label"));
        System.out.println("Number of options:" + options.size()); // 14

        // 3) printing options from dropdown
        /*
         * for(WebElement op:options)
         * {
         * System.out.println(op.getText());
         * }
         */

        // 4) select multiple options
        for (WebElement op : options) {
            String option = op.getText();

            if (option.equals("Java") || option.equals("Python") || option.equals("MySQL")) {
                op.click();
            }
        }
    }

    @Test
    public void dropdownDemo2() throws InterruptedException {
        driver.get("http://www.google.com");
        driver.manage().deleteAllCookies(); // deletes all the cookies in your browser

        driver.findElement(By.name("q")).sendKeys("selenium");// Search box
        Thread.sleep(5000);

        List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li//div[@role='option']"));

        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(i).getText().equals("selenium")) {
                list.get(i).click();
                break;
            }
        }
    }

    @Test
    public void dropdownDemo3() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php");
        // Login steps
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        // clicking on PIM
        driver.findElement(By.xpath("//span[normalize-space()='PIM']")).click(); // PIM

        // clicked on dropdown
        driver.findElement(By.xpath(
                "//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']/div[@class='oxd-table-filter-area']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[6]/div[1]/div[2]/div[1]/div[1]"))
                .click();
        Thread.sleep(5000);

        // select single option
        // driver.findElement(By.xpath("//span[normalize-space()='Financial
        // Analyst']")).click();

        // count number of options
        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//span"));

        System.out.println("Number of options:" + options.size()); // 29

        // printing options
        for (WebElement op : options) {
            System.out.println(op.getText());
        }

    }

    @Test
    public void dropdownDemo4() {
        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement drpCountryEle = driver.findElement(By.xpath("//select[@id='country']"));
        Select drpCountry = new Select(drpCountryEle);

        // select option from the drop down

        // drpCountry.selectByVisibleText("France");
        // drpCountry.selectByValue("japan");
        // drpCountry.selectByIndex(2);

        // capture the options from the dropdown

        List<WebElement> options = drpCountry.getOptions();
        System.out.println("Number of options in a drop down:" + options.size()); // 10

        // printing the options
        /*
         * for(int i=0;i<options.size();i++)
         * {
         * System.out.println(options.get(i).getText());
         * 
         * }
         */

        // enhanced for loop
        for (WebElement op : options) {
            System.out.println(op.getText());

        }
    }
}
