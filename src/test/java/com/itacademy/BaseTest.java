package com.itacademy;

import com.itacademy.listeners.TestNGListener;
import com.itacademy.utils.DriverManager;
import com.itacademy.utils.ScreenshotUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class BaseTest {

    private static final String BROWSER = "chrome";


    @AfterMethod
    public void closeSession() {
        ScreenshotUtils.makeScreenshot();
        DriverManager.closeSession();
    }
}
