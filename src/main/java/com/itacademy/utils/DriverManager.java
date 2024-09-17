package com.itacademy.utils;

import com.itacademy.enums.PropertiesValue;
import com.itacademy.listeners.SeleniumListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;

public class DriverManager {

    private static volatile WebDriver driver;
    private  static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    private DriverManager() {

    }

    public synchronized static WebDriver getDriver() {
        if (threadLocal.get() == null) {
            driver = DriverFactory.createDriver(ConfigurationReader.getProperty(PropertiesValue.BROWSER.BROWSER));
            EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator(new SeleniumListener());
            driver = decorator.decorate(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //will wait for element
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); //will wait for page to load
            threadLocal.set(driver);
        }
        return threadLocal.get();
    }
    public synchronized static void closeSession(){
        threadLocal.get().quit();
        threadLocal.set(null);
    }
}
