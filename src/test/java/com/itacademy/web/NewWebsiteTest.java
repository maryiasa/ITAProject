package com.itacademy.web;

import com.itacademy.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


@Log4j2
public class NewWebsiteTest extends BaseTest {

    public static final Logger log =  LogManager.getLogger(NewWebsiteTest.class);

    @Test
    public void frameTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        Waiters.wait(3000);
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("//*[text() = 'All Access plan']"));
        log.info("List before: " + list.size());
        DriverManager.getDriver().switchTo().frame("courses-iframe");
        list = DriverManager.getDriver().findElements(By.xpath("//*[text() = 'All Access plan']"));
        log.info("List after: " + list.size());
        DriverManager.getDriver().switchTo().defaultContent();

    }

    @Test
    public void alertTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        DriverManager.getDriver().findElement(By.id("name")).sendKeys("Maria");
        DriverManager.getDriver().findElement(By.id("alertbtn")).click();
        Alert alert = DriverManager.getDriver().switchTo().alert();
        log.info(alert.getText());
        alert.accept();

    }

    @Test
    public void autocompleteTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        DriverManager.getDriver().findElement(By.id("autocomplete")).sendKeys("India");
        Waiters.wait(1000);
        DriverManager.getDriver().findElement(By.xpath("//div[text() = 'British Indian Ocean Territory' and @class = 'ui-menu-item-wrapper']")).click();
        Waiters.wait(1000);
        log.info(DriverManager.getDriver().getPageSource());

    }

    @Test
    public void confirmTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement nameInput = DriverManager.getDriver().findElement(By.id("name"));
        log.info(nameInput.getAttribute("placeholder"));
        nameInput.sendKeys("Maria");
        DriverManager.getDriver().findElement(By.id("confirmbtn")).click();
        Alert alert = DriverManager.getDriver().switchTo().alert();
        log.info(alert.getText());
        alert.accept();
        DriverManager.getDriver().findElement(By.id("confirmbtn")).click();
        Alert alert1 = DriverManager.getDriver().switchTo().alert();
        log.info(alert1.getText());
        alert1.dismiss();

    }

    @Test
    public void hideTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = DriverManager.getDriver().findElement(By.id("displayed-text"));
        log.info(showHide.getAttribute("placeholder"));
        Assert.assertTrue(showHide.isDisplayed());
        DriverManager.getDriver().findElement(By.id("hide-textbox")).click();
        Assert.assertFalse(showHide.isDisplayed());
    }

    @Test
    public void showTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement showHide = DriverManager.getDriver().findElement(By.id("displayed-text"));
        log.info(showHide.getAttribute("placeholder"));
        DriverManager.getDriver().findElement(By.id("hide-textbox")).click();
        Assert.assertFalse(showHide.isDisplayed());
        DriverManager.getDriver().findElement(By.id("show-textbox")).click();
        Assert.assertTrue(showHide.isDisplayed());
    }

    @Test
    public void newTabTest() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        String mainWnd = DriverManager.getDriver().getWindowHandle();
        DriverManager.getDriver().findElement(By.id("opentab")).click();
        Waiters.wait(3000);

        for (String windowString: DriverManager.getDriver().getWindowHandles())
            if (!windowString.equals(mainWnd))
                DriverManager.getDriver().switchTo().window(windowString);

        Assert.assertTrue(DriverManager.getDriver().findElement(By.xpath("//*[text() = 'Access all our Courses']")).isDisplayed());

        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(mainWnd);
    }

    @Test
    public void mouseHover() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        ActionsUtils.hoverAndClick(DriverManager.getDriver(), DriverManager.getDriver().findElement(By.id("mousehover")), DriverManager.getDriver().findElement(By.xpath("//*[text() = 'Top']")));
        ActionsUtils.hoverAndClick(DriverManager.getDriver(), DriverManager.getDriver().findElement(By.id("mousehover")), DriverManager.getDriver().findElement(By.xpath("//*[text() = 'Reload']")));
        ActionsUtils.hover(DriverManager.getDriver(), DriverManager.getDriver().findElement(By.id("mousehover")));

    }

    @Test
    public void getTextByJS() {
        String text = "Maria";
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        DriverManager.getDriver().findElement(By.id("name")).sendKeys(text);
        Assert.assertEquals(JSExecutorUtils.getTextByID(DriverManager.getDriver(), "name"), text);
    }

    @Test
    public void listenerTest() {
        String text = "Maria";
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        DriverManager.getDriver().findElement(By.xpath("//*[@value= 'radio1']")).click();
        DriverManager.getDriver().findElement(By.xpath("//*[@value= 'radio2']")).click();
        DriverManager.getDriver().findElement(By.xpath("//*[@value= 'radio3']")).click();
        DriverManager.getDriver().findElement(By.id("name")).sendKeys(text);
        Assert.assertEquals(JSExecutorUtils.getTextByID(DriverManager.getDriver(), "name"), text);


    }

}
