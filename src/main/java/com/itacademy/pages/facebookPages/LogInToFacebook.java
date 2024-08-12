package com.itacademy.pages.facebookPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInToFacebook {
    private WebDriver driver;

    @FindBy(className = "_9axz")
    private  WebElement pageName;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "pass")
    private WebElement pwdInput;

    @FindBy(id = "loginbutton")
    private WebElement logInBtn;

    @FindBy(css = "._42ft._4jy0._82go._4jy6._517h._51sy")
    private WebElement logInWithGoogleBtn;

    @FindBy(id = "login_link")
    private WebElement forgottenPwdBtn;

    @FindBy(xpath = "//*[text() = \"Find your account and log in.\"]")
    private WebElement findYourAccAndLogInBtn;

    @FindBy(className = "_9ay7")
    private WebElement errorText;

    String errorTxt = "The email address or mobile number you entered isn't connected to an account. Find your account and log in.";

    public LogInToFacebook(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageName() {
        return pageName.getText();
    }

    public LogInToFacebook clickLogInBtn() {
        logInBtn.click();
        return new LogInToFacebook(driver);
    }

    public FindYourAccount clickForgottenPwdBtn() {
        forgottenPwdBtn.click();
        return new FindYourAccount(driver);
    }

    public String getErrorText() {
        return errorText.getText();
    }

    public String getErrorTxt() {
        return errorTxt;
    }
}
