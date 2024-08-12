package com.itacademy.pages.facebookPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindYourAccount {
    private WebDriver driver;

    @FindBy(xpath = "(//*[text() = 'Find Your Account'])[2]")
    private WebElement pageName;

    @FindBy(id = "identify_email")
    private WebElement emailInput;

    @FindBy(id = "did_submit")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[text() = 'Cancel']")
    private WebElement cancelBtn;

    public FindYourAccount(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSearchBtn() {
        searchBtn.click();
    }

    public void clickCancelBtn() {
        cancelBtn.click();
    }

    public String getPageName() {
        return pageName.getText();
    }
}
