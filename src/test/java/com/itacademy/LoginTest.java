package com.itacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class LoginTest extends  BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(LoginTest.class);

    @Test
    public void  positivelogin() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameInput = driver.findElement(By.xpath("//div[1]/input"));
        nameInput.sendKeys("Maria");
        WebElement emailInput = driver.findElement(By.xpath("//div[2]/input"));
        emailInput.sendKeys("email@gmail.com");
        WebElement pwdInput = driver.findElement(By.id("exampleInputPassword1"));
        pwdInput.sendKeys("Khfgjghjkhjl7!khlh");
        WebElement loveIceCreamsCheckbox = driver.findElement(By.xpath("//*[@id=\"exampleCheck1\"]"));
        WebElement loveIceCreamsLabel = driver.findElement(By.xpath("//div[4]"));
        String checkboxLabel = loveIceCreamsLabel.getText();
        LOGGER.info(checkboxLabel);

        if (!Objects.equals(checkboxLabel, "Check me out if you Love IceCreams!")) {
            LOGGER.error("Expected text:  Check me out if you Love IceCreams!. Actual text: " + checkboxLabel);
        }

        loveIceCreamsCheckbox.click();
        Assert.assertTrue(loveIceCreamsCheckbox.isSelected());

        Select selectGender = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        selectGender.selectByVisibleText("Female");
        WebElement employmentStatusRadioBtn = driver.findElement(By.id("inlineRadio2"));
        employmentStatusRadioBtn.click();
        WebElement dateOfBirthCalendar = driver.findElement(By.xpath("//div[7]/input"));
        dateOfBirthCalendar.sendKeys("08-06-1998");
        WebElement submitBtn = driver.findElement(By.xpath("//div/form/input"));
        submitBtn.click();

    }

}
