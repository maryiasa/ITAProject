package com.itacademy.pages.facebookPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUp {
    private WebDriver driver;

    public SignUp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, SignUp.class);
    }
}
