package com.itacademy.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {

    public static void hoverAndClick(WebDriver driver, WebElement hoverElement, WebElement clickElement) {
        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        Waiters.wait(500);
        builder.moveToElement(clickElement).click().perform();
    }

    public  static void hover(WebDriver driver, WebElement hoverElement) {
        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
    }
}
