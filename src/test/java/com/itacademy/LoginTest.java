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
    public void  positiveLogin() {
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

        if (!Objects.equals(checkboxLabel, "Check me out if you Love IceCreams!")) {
            LOGGER.error("Expected text:  Check me out if you Love IceCreams!. Actual text: " + checkboxLabel);
        } else LOGGER.info(checkboxLabel);

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
        WebElement successAlert = driver.findElement(By.xpath("//*[@class = 'alert alert-success alert-dismissible']"));
        String successAlertTxt = successAlert.getText();

        if (!Objects.equals(successAlertTxt, "Success! The Form has been submitted successfully!.")) {
            LOGGER.error("Expected text:  Success! The Form has been submitted successfully!.. Actual text: " + successAlertTxt);
        } else LOGGER.info(successAlertTxt);

    }

    @Test
    public void  negativeLogin() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameInput = driver.findElement(By.xpath("//div[1]/input"));
        nameInput.click();
        Select selectGender = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        selectGender.selectByVisibleText("Female");
        WebElement nameAlert = driver.findElement(By.xpath("//*[@class = 'alert alert-danger'][text() = 'Name is required']"));
        Assert.assertTrue(nameAlert.isDisplayed());
        WebElement emailInput = driver.findElement(By.xpath("//div[2]/input"));
        emailInput.click();
        //emailInput.sendKeys("dskfljvdflkb");
        //nameInput.click();
        //emailInput.clear();
        WebElement pwdInput = driver.findElement(By.id("exampleInputPassword1"));
        pwdInput.click();
        WebElement emailAlert = driver.findElement(By.xpath("//*[@class = 'alert alert-danger'][text() = 'Email is required']"));
        Assert.assertTrue(emailAlert.isDisplayed());

        WebElement submitBtn = driver.findElement(By.xpath("//div/form/input"));
        LOGGER.error("One or more required fields are empty.");
        Assert.assertFalse(submitBtn.isEnabled());
    }

}
