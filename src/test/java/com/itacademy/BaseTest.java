package com.itacademy;

import com.itacademy.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public  void setUp() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //will wait for element
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); //will wait for page to load
    }

    @AfterMethod
    public void closeSession() {
        driver.quit();
    }
}
