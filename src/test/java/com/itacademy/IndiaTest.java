package com.itacademy;

import com.itacademy.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


@Log4j2
public class IndiaTest extends BaseTest{

    private static final Logger log = LogManager.getLogger(IndiaTest.class);

    @Test
    public void  login() throws InterruptedException {

        DriverManager.getDriver().get("https://rahulshettyacademy.com/angularpractice/");

        //input field
        WebElement nameInputField = DriverManager.getDriver().findElement(By.name("name"));
        nameInputField.sendKeys("Maria");
        WebElement emailInputField =  DriverManager.getDriver().findElement(By.name("email"));
        emailInputField.sendKeys("");
        DriverManager.getDriver().findElement(By.xpath("//*[@class = 'btn btn-success']")).click();
       // emailInputField.submit();
        WebElement alertText = DriverManager.getDriver().findElement(By.cssSelector("div.alert.alert-danger"));
        Assert.assertEquals(alertText.getText(), "Email is required");

        //checkbox
        WebElement checkbox = DriverManager.getDriver().findElement(By.xpath("//*[@type='checkbox']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
        checkbox.click();
        Assert.assertFalse(checkbox.isSelected());

        //drop-down
        Select selectElement = new Select(DriverManager.getDriver().findElement(By.id("exampleFormControlSelect1")));
        selectElement.selectByIndex(0);
        selectElement.selectByVisibleText("Male");
        selectElement.selectByVisibleText("Female");
        log.info(selectElement.getFirstSelectedOption().getText());
        Assert.assertEquals(selectElement.getFirstSelectedOption().getText(), "Female");

        Waiters.wait(3000);

        //WebElement dynamicElement = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElement_id")));

    }

}
