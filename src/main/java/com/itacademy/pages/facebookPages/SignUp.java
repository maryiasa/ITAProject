package com.itacademy.pages.facebookPages;

import com.itacademy.utils.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class SignUp {

    public SignUp() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}
