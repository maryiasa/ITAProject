package com.itacademy;

import com.itacademy.utils.JSExecutorUtils;
import com.itacademy.utils.Waiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class CartTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(CartTest.class);

    @Test
    public void addProductToCart() {

        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");

        WebElement firstProduct = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/p"));
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/button"));
        String firstProductName = firstProduct.getText();

        addBtn.click();

        WebElement firstProductCart = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[1]/p[1]"));
        String firstProdictNameCart = firstProductCart.getText();

        if (Objects.equals(firstProductName, firstProdictNameCart)) {
            LOGGER.info("firstProductName = " + firstProductName + ". firstProductCart = " + firstProdictNameCart);
        } else LOGGER.error("Name of the Product in the cart doesn't match Name of added item. firstProductName = " + firstProductName + ". firstProductCart = " + firstProdictNameCart);
    }

    @Test
    public void removeItemFromCart() {
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");

        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/button"));
        addBtn.click();

        WebElement removeProductFromCartBtn = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/button"));
        WebElement subTotal = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/div/p[1]"));
        removeProductFromCartBtn.click();
        String subTotalAfterRemoveValue = subTotal.getText();

        if (subTotalAfterRemoveValue.equals("$ 0.01")) {
            LOGGER.info("Item is removed. Subtotal = " + subTotalAfterRemoveValue);
        } else LOGGER.error("Subtotal should be = $ 0.00. Actual value is " + subTotalAfterRemoveValue);
    }

    @Test
    public void checkProduct() {
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        Waiters.wait(3000);
        List<WebElement> addToCartBtn = driver.findElements(By.xpath("//*[text()='Add to cart']"));
        for (WebElement webElement: addToCartBtn)
        {
            JSExecutorUtils.click(driver, webElement);
        }

    }

}
