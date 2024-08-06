package com.itacademy;

import com.itacademy.utils.ActionsUtils;
import com.itacademy.utils.ScreenshotUtils;
import com.itacademy.utils.Waiters;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NewWebsiteTest extends BaseTest{

    @Test
    public void  frametest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Waiters.wait(3000);
        List<WebElement> list = driver.findElements(By.xpath("//*[text() = 'All Access plan']"));
        System.out.println(list.size());
        ScreenshotUtils.makeScreenshot(driver, "frame_");
        driver.switchTo().frame("courses-iframe");
        System.out.println(list.size());
        driver.switchTo().defaultContent();

    }

    @Test
    public void alertTest() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept(); //[ok]
        //alert.dismiss(); //[cancel]

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
