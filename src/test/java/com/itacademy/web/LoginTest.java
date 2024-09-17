package com.itacademy.web;

import com.itacademy.pages.protoCommercePages.LogInPagePT;
import com.itacademy.utils.ScreenshotUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

@Log4j2
public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void  positiveLogin() {
        LogInPagePT logInPagePT = new LogInPagePT();
        logInPagePT.open();
        logInPagePT.getNameInput().sendKeys("Maria");
        logInPagePT.getEmailInput().sendKeys("email@gmail.com");
        logInPagePT.getPwdInput().sendKeys("Khfgjghjkhjl7!khlh");
        String checkboxLabel = logInPagePT.getLoveIceCreamsLabel();
        if (!Objects.equals(checkboxLabel, "Check me out if you Love IceCreams!")) {
            log.error("Expected text:  Check me out if you Love IceCreams!. Actual text: " + checkboxLabel);
        } else log.info(checkboxLabel);
        WebElement loveIceCreamsCheckbox = logInPagePT.getLoveIceCreamsCheckbox();
        ScreenshotUtils.makeScreenshot();
        loveIceCreamsCheckbox.click();
        Assert.assertTrue(loveIceCreamsCheckbox.isSelected());
        Select selectGender = new Select(logInPagePT.getSelectGender());
        selectGender.selectByVisibleText("Female");
        logInPagePT.getEmploymentStatusRadioBtn().click();
        logInPagePT.getDateOfBirthCalendar().sendKeys("08-06-1998");
        logInPagePT.getSubmitBtn().click();
        String successAlertTxt = logInPagePT.getSuccessAlert();
        if (!Objects.equals(successAlertTxt, "Success! The Form has been submitted successfully!.")) {
            log.error("Expected text:  Success! The Form has been submitted successfully!.. Actual text: " + successAlertTxt);
        } else log.info(successAlertTxt);

    }

    @Test
    public void  negativeLogin() {
        LogInPagePT logInPagePT = new LogInPagePT();
        logInPagePT.open();
        logInPagePT.getNameInput().click();
        Select selectGender = new Select(logInPagePT.getSelectGender());
        selectGender.selectByVisibleText("Female");
        Assert.assertTrue(logInPagePT.getNameAlert().isDisplayed());
        logInPagePT.getEmailInput().click();
        logInPagePT.getPwdInput().click();
        Assert.assertTrue(logInPagePT.getEmailAlert().isDisplayed());
        if (!logInPagePT.getSubmitBtn().isEnabled()) {
            log.error("One or more required fields are empty.");
        }
        Assert.assertFalse(logInPagePT.getSubmitBtn().isEnabled());
    }

}
