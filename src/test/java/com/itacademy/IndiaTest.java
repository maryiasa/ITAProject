package com.itacademy;

import com.itacademy.utils.Waiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IndiaTest extends BaseTest{

    private static final Logger LOGGER = LogManager.getLogger(IndiaTest.class);

    @Test
    public void  login() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        //input field
        WebElement nameInputField = driver.findElement(By.name("name"));
        nameInputField.sendKeys("Maria");
        WebElement emailInputField =  driver.findElement(By.name("email"));
        emailInputField.sendKeys("");
        emailInputField.submit();
        WebElement alertText = driver.findElement(By.cssSelector(".alert.alert-danger"));
        Assert.assertEquals(alertText.getText(), "Email is required");

        //checkbox
        WebElement checkbox = driver.findElement(By.xpath("//*[@type='checkbox']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
        checkbox.click();
        Assert.assertFalse(checkbox.isSelected());

        //drop-down
        Select selectElement = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        selectElement.selectByIndex(0);
        selectElement.selectByVisibleText("Male");
        selectElement.selectByVisibleText("Female");
        LOGGER.info(selectElement.getFirstSelectedOption().getText());
        Assert.assertEquals(selectElement.getFirstSelectedOption().getText(), "Female");

        Waiters.wait(3000);

        WebElement dynamicElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElement_id")));

    }

}
