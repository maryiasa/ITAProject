package com.itacademy;

import com.itacademy.pages.facebookPages.FindYourAccount;
import com.itacademy.pages.facebookPages.HomePage;
import com.itacademy.pages.facebookPages.LogInToFacebook;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class FacebookTest extends BaseTest {

    @Test
    public void test1 () {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("(//*[text() = 'Allow all cookies'])[2]")).click();
    }


    @Test
    public void loadHomePageElements() {
        driver.get("https://www.facebook.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickCookies();
        String sloganText = homePage.getSlogan();
        String logInBtnText = homePage.getLogInBtn();
        String createNewAccBtnText = homePage.getCreateNewAccBtn();
        String forgottenPwdText = homePage.getForgottenPwdBtn();
        Assert.assertEquals(sloganText, homePage.getFacebookSloganTxt());
        Assert.assertEquals(logInBtnText, "Log in");
        Assert.assertEquals(createNewAccBtnText, "Create new account");
        Assert.assertEquals(forgottenPwdText, "Forgotten password?");
    }

    @Test
    public void negativeLogin() {
        driver.get("https://www.facebook.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickCookies();
        LogInToFacebook logIn = homePage.clickLogInBtn();
        Assert.assertEquals(logIn.getPageName(), "Log in to Facebook");
        Assert.assertEquals(logIn.getErrorText(), logIn.getErrorTxt());
    }

    @Test
    public void forgottenPwd() {
        driver.get("https://www.facebook.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickCookies();
        FindYourAccount findYourAccount = homePage.clickForgottenPwdBtn();
        Assert.assertEquals(findYourAccount.getPageName(), "Find Your Account");
        findYourAccount.clickSearchBtn();
    }

}
