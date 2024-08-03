package com.itacademy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public  void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/maryiasahanovich/Drivers/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //will wait for element
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); //will wait for page to load
    }

    @AfterMethod
    public void closeSession() {
        driver.quit();
    }
}
