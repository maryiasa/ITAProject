package com.itacademy.web;

import com.itacademy.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void firstTest () {

        DriverManager.getDriver().get("https://react-shopping-cart-67954.firebaseapp.com/");
        WebElement element = DriverManager.getDriver().findElement(By.xpath("//*[@class = 'sc-bj2vay-1 hcyKTa'][3]"));
        element.click();
    }
}
