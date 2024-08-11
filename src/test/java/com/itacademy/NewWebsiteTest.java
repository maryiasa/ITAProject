package com.itacademy;

import com.itacademy.utils.ActionsUtils;
import com.itacademy.utils.JSExecutorUtils;
import com.itacademy.utils.ScreenshotUtils;
import com.itacademy.utils.Waiters;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


@Log4j2
public class NewWebsiteTest extends BaseTest{

    public static final Logger log =  LogManager.getLogger(NewWebsiteTest.class);

    @Test
    public void frametest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Waiters.wait(3000);
        List<WebElement> list = driver.findElements(By.xpath("//*[text() = 'All Access plan']"));
        log.info(list.size());
        ScreenshotUtils.makeScreenshot(driver, "frame_");
        driver.switchTo().frame("courses-iframe");
        log.info(list.size());
        driver.switchTo().defaultContent();

    }

    @Test
    public void alertTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys("Maria");
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        log.info(alert.getText());
        alert.accept();

    }

    @Test
    public void confirmTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement nameInput = driver.findElement(By.id("name"));
        log.info(nameInput.getAttribute("placeholder"));
        nameInput.sendKeys("Maria");
        driver.findElement(By.id("confirmbtn")).click();
        Alert alert = driver.switchTo().alert();
        log.info(alert.getText());
        alert.accept();
        driver.findElement(By.id("confirmbtn")).click();
        Alert alert1 = driver.switchTo().alert();
        log.info(alert1.getText());
        alert1.dismiss();

    }

    @Test
    public void hideTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = driver.findElement(By.id("displayed-text"));
        log.info(showHide.getAttribute("placeholder"));
        Assert.assertTrue(showHide.isDisplayed());
        driver.findElement(By.id("hide-textbox")).click();
        Assert.assertFalse(showHide.isDisplayed());
    }

    @Test
    public void showTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = driver.findElement(By.id("displayed-text"));
        log.info(showHide.getAttribute("placeholder"));
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

    @Test
    public void getTextByJS() {
        String text = "Maria";
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys(text);
        Assert.assertEquals(JSExecutorUtils.getTextByID(driver, "name"), text);
    }

    @Test
    public void listenerTest() {
        String text = "Maria";
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.xpath("//*[@value= 'radibutton1']")).click();
        driver.findElement(By.xpath("//*[@value= 'radibutton2']")).click();
        driver.findElement(By.xpath("//*[@value= 'radibutton3']")).click();
        driver.findElement(By.id("name")).sendKeys(text);
        Assert.assertEquals(JSExecutorUtils.getTextByID(driver, "name"), text);


    }

}
