package com.itacademy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class JSExecutorUtils {

    public static void click(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static String getTextByID(WebDriver driver, String name ) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        return (String) executor.executeScript("return document.getElementById(\"" + name + "\").value");
    }

}
