package com.itacademy.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceTest {

    @Test
    public void androidTest() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setApp("/Users/maryiasahanovich/IdeaProjects/ITAProject/saucelabs.apk")
                .setAppActivity("com.swaglabsmobileapp.MainActivity");
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );
        try {
            Thread.sleep(10000);
            WebElement el1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
            WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-Password"));
            WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
            el1.sendKeys("Maryia");
            el2.sendKeys("12345");
            el3.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }


}

