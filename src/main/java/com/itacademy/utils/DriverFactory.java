package com.itacademy.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver createDriver(String browserName) {
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/maryiasahanovich/Drivers/chromedriver");
            return new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/Users/maryiasahanovich/Drivers/geckodriver");
            return new FirefoxDriver();
        } else {
           return null;
        }
    }
}
