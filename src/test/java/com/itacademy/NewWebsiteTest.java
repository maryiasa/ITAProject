package com.itacademy;

import com.itacademy.utils.ActionsUtils;
import com.itacademy.utils.ScreenshotUtils;
import com.itacademy.utils.Waiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NewWebsiteTest extends BaseTest{

    private static final Logger LOGGER = LogManager.getLogger(NewWebsiteTest.class);

    @Test
    public void  frametest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Waiters.wait(3000);
        List<WebElement> list = driver.findElements(By.xpath("//*[text() = 'All Access plan']"));
        LOGGER.info(list.size());
        ScreenshotUtils.makeScreenshot(driver, "frame_");
        driver.switchTo().frame("courses-iframe");
        LOGGER.info(list.size());
        driver.switchTo().defaultContent();

    }

    @Test
    public void alertTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys("Maria");
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        LOGGER.info(alert.getText());
        alert.accept();

    }

    @Test
    public void confirmTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement nameInput = driver.findElement(By.id("name"));
        LOGGER.info(nameInput.getAttribute("placeholder"));
        nameInput.sendKeys("Maria");
        driver.findElement(By.id("confirmbtn")).click();
        Alert alert = driver.switchTo().alert();
        LOGGER.info(alert.getText());
        alert.accept();
        driver.findElement(By.id("confirmbtn")).click();
        Alert alert1 = driver.switchTo().alert();
        LOGGER.info(alert1.getText());
        alert1.dismiss();

    }

    @Test
    public void hideTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = driver.findElement(By.id("displayed-text"));
        LOGGER.info(showHide.getAttribute("placeholder"));
        Assert.assertTrue(showHide.isDisplayed());
        driver.findElement(By.id("hide-textbox")).click();
        Assert.assertFalse(showHide.isDisplayed());
    }

    @Test
    public void showTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = driver.findElement(By.id("displayed-text"));
        LOGGER.info(showHide.getAttribute("placeholder"));
        driver.findElement(By.id("hide-textbox")).click();
        Assert.assertFalse(showHide.isDisplayed());
        driver.findElement(By.id("show-textbox")).click();
        Assert.assertTrue(showHide.isDisplayed());
    }

    @Test
    public void newTabTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        String mainWnd = driver.getWindowHandle();
        driver.findElement(By.id("opentab")).click();
        Waiters.wait(3000);

        for (String windowString: driver.getWindowHandles())
            if (!windowString.equals(mainWnd))
                driver.switchTo().window(windowString);

        Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'Access all our Courses']")).isDisplayed());

        driver.close();
        driver.switchTo().window(mainWnd);
    }

    @Test
    public void mouseHover() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        ActionsUtils.hoverAndClick(driver, driver.findElement(By.id("mousehover")), driver.findElement(By.xpath("//*[text() = 'Top']")));
        ActionsUtils.hoverAndClick(driver, driver.findElement(By.id("mousehover")), driver.findElement(By.xpath("//*[text() = 'Reload']")));
        ActionsUtils.hover(driver, driver.findElement(By.id("mousehover")));

    }

}
