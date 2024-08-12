package com.itacademy.pages.facebookPages;

import com.itacademy.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "(//*[text() = 'Allow all cookies'])[2]")
    private WebElement allowCookiesBtn;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "pass")
    private WebElement pwdInput;

    @FindBy(xpath = "//*[@name = 'login']")
    private WebElement logInBtn;

    @FindBy(xpath = "//*[text() = 'Forgotten password?']")
    private WebElement forgottenPwdBtn;

    @FindBy(xpath = "//*[text() = 'Create new account']")
    private WebElement createNewAccBtn;

    @FindBy(xpath = "//*[text() = 'Create a Page']")
    private WebElement createPageForCelebrityOrBusinessBtn;

    @FindBy(css = ".fb_logo._8ilh.img")
    private WebElement logoImg;

    @FindBy(className = "_8eso")
    private WebElement slogan;

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void open() {
        DriverManager.getDriver().get("https://www.facebook.com/");
    }

    String facebookSloganTxt = "Facebook helps you connect and share with the people in your life.";

    public void clickCookies() {
        allowCookiesBtn.click();
    }

    public LogInToFacebook clickLogInBtn() {
        logInBtn.click();
        return new LogInToFacebook();
    }

    public FindYourAccount clickForgottenPwdBtn() {
        forgottenPwdBtn.click();
        return new FindYourAccount();
    }

    public SignUp clickCreateNewAccBtn() {
        createNewAccBtn.click();
        return new SignUp();
    }



    public String getLogInBtn() {
        return logInBtn.getText();
    }

    public String getCreateNewAccBtn() {
        return createNewAccBtn.getText();
    }

    public String getSlogan() {
        return slogan.getText();
    }

    public String getForgottenPwdBtn() {
        return forgottenPwdBtn.getText();
    }

    public String getFacebookSloganTxt() {
        return facebookSloganTxt;
    }
}
