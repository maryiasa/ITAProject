package com.itacademy.pages.protoCommercePages;

import com.itacademy.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPagePT {

    @FindBy(xpath = "//div[1]/input")
    private WebElement nameInput;

    @FindBy(xpath = "//div[2]/input")
    private WebElement emailInput;

    @FindBy(id = "exampleInputPassword1")
    private WebElement pwdInput;

    @FindBy(xpath = "//*[@id=\"exampleCheck1\"]")
    private WebElement loveIceCreamsCheckbox;

    @FindBy(xpath = "//div[4]")
    private WebElement loveIceCreamsLabel;

    @FindBy(id = "exampleFormControlSelect1")
    private WebElement selectGender;

    @FindBy(id = "inlineRadio2")
    private WebElement employmentStatusRadioBtn;

    @FindBy(xpath = "//div[7]/input")
    private WebElement dateOfBirthCalendar;

    @FindBy(xpath = "//div/form/input")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[@class = 'alert alert-success alert-dismissible']")
    private WebElement successAlert;

    @FindBy(xpath = "//*[@class = 'alert alert-danger'][text() = 'Name is required']")
    private WebElement nameAlert;

    @FindBy(xpath = "//*[@class = 'alert alert-danger'][text() = 'Email is required']")
    private WebElement emailAlert;

    public LogInPagePT() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void open() {
        DriverManager.getDriver().get("https://rahulshettyacademy.com/angularpractice/");
    }

    public WebElement getNameInput() {
        return nameInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPwdInput() {
        return pwdInput;
    }

    public WebElement getLoveIceCreamsCheckbox() {
        return loveIceCreamsCheckbox;
    }

    public String getLoveIceCreamsLabel() {
        return loveIceCreamsLabel.getText();
    }

    public WebElement getSelectGender() {
        return selectGender;
    }

    public WebElement getEmploymentStatusRadioBtn() {
        return employmentStatusRadioBtn;
    }

    public WebElement getDateOfBirthCalendar() {
        return dateOfBirthCalendar;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }

    public String getSuccessAlert() {
        return successAlert.getText();
    }

    public WebElement getNameAlert() {
        return nameAlert;
    }

    public WebElement getEmailAlert() {
        return emailAlert;
    }
}
