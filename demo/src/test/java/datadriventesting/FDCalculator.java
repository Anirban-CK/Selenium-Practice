package datadriventesting;

import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import datadriventesting.utils.ExcelUtils;

public class FDCalculator extends Base {
    @Test
    public void testFDPrediction() throws IOException, InterruptedException {
        mywait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://cleartax.in/s/simple-compound-interest-calculator");

        String filePath = System.getProperty("user.dir") + "/testdata/caldata.xlsx";
        // String filePath = System.getProperty("user.dir") + "/testdata/caldata2.xlsx";
        mywait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Simple And Compound Interest Calculator']")));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        jsExecutor.executeScript("window.scrollTo(0, - document.body.scrollHeight);");
        jsExecutor.executeScript("window.scrollBy(0,370)");

        int rows = ExcelUtils.getRowCount(filePath, "Sheet1");

        for (int r = 1; r <= rows; r++) {
            String principal = ExcelUtils.getCellData(filePath, "Sheet1", r, 0);
            String rate = ExcelUtils.getCellData(filePath, "Sheet1", r, 1);
            String period1 = ExcelUtils.getCellData(filePath, "Sheet1", r, 2);
            String period2 = ExcelUtils.getCellData(filePath, "Sheet1", r, 3);
            // String frequ = ExcelUtils.getCellData(filePath, "Sheet1", r, 4);
            String maturity = ExcelUtils.getCellData(filePath, "Sheet1", r, 5);
            // String expected = ExcelUtils.getCellData(filePath, "Sheet1", r, 6);

            System.out.print(principal + " - ");
            System.out.print(rate + " - ");
            System.out.print(period1 + " - ");
            System.out.print(period2 + " - ");
            System.out.print(maturity);

            driver.findElement(By.xpath("//input[@id='principleAmount']")).clear();
            driver.findElement(By.xpath("//input[@id='principleAmount']")).sendKeys(principal);

            driver.findElement(By.xpath("//input[@id='annualrate']")).clear();
            driver.findElement(By.xpath("//input[@id='annualrate']")).sendKeys(rate);

            WebElement drpPeriodUnit = driver.findElement(By.xpath("//select[@id='periodUnit']"));
            Select periodUnit = new Select(drpPeriodUnit);
            // System.out.print(false + " " + periodUnit);
            if (period2.toLowerCase().equals("years")) {
                periodUnit.selectByContainsVisibleText("Years");
            } else if (period2.toLowerCase().equals("months")) {
                Thread.sleep(1000);
                periodUnit.selectByContainsVisibleText("Months");
            } else if (period2.toLowerCase().equals("days")) {
                Thread.sleep(1000);
                periodUnit.selectByContainsVisibleText("Days");
            }

            driver.findElement(By.xpath("//input[@id='periodInDigit']")).clear();
            driver.findElement(By.xpath("//input[@id='periodInDigit']")).sendKeys(period1);

            WebElement totalValueEle = driver
                    .findElement(By.xpath("//div[normalize-space()='Total Value']/..//span[2]"));
            String totalValue = totalValueEle.getText().replace(",", "");
            System.out.println("[ACTUAL] - " + totalValue);

            if (Double.parseDouble(maturity) == Double.parseDouble(totalValue)) {
                System.out.println("PASSED");
                ExcelUtils.setCellData(filePath, "Sheet1", r, 7, "Passed");
                ExcelUtils.fillGreenColor(filePath, "Sheet1", r, 7);
            } else {
                System.out.println("FAILED");
                ExcelUtils.setCellData(filePath, "Sheet1", r, 7, "FAILED");
                ExcelUtils.fillRedColor(filePath, "Sheet1", r, 7);
            }
        }
    }
}
