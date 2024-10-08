package com.itacademy.web;

import com.itacademy.utils.DriverManager;
import com.itacademy.utils.JSExecutorUtils;
import com.itacademy.utils.Waiters;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

@Log4j2
public class CartTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(CartTest.class);

    @Test
    public void addProductToCart() {

        DriverManager.getDriver().get("https://react-shopping-cart-67954.firebaseapp.com/");
        WebElement firstProduct = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/p"));
        WebElement addBtn = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/button"));
        String firstProductName = firstProduct.getText();
        addBtn.click();
        WebElement firstProductCart = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[1]/p[1]"));
        String firstProdictNameCart = firstProductCart.getText();
        if (Objects.equals(firstProductName, firstProdictNameCart)) {
            log.info("firstProductName = " + firstProductName + ". firstProductCart = " + firstProdictNameCart);
        } else log.error("Name of the Product in the cart doesn't match Name of added item. firstProductName = " + firstProductName + ". firstProductCart = " + firstProdictNameCart);
    }

    @Test
    public void removeItemFromCart() {
        DriverManager.getDriver().get("https://react-shopping-cart-67954.firebaseapp.com/");
        WebElement addBtn = DriverManager.getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/main/main/div/div[1]/button"));
        addBtn.click();
        WebElement removeProductFromCartBtn = DriverManager.getDriver().findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/button"));
        WebElement subTotal = DriverManager.getDriver().findElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/div/p[1]"));
        removeProductFromCartBtn.click();
        String subTotalAfterRemoveValue = subTotal.getText();
        if (subTotalAfterRemoveValue.equals("$ 0.01")) {
            log.info("Item is removed. Subtotal = " + subTotalAfterRemoveValue);
        } else log.error("Subtotal should be = $ 0.00. Actual value is " + subTotalAfterRemoveValue);
    }

    @Test
    public void checkProduct() {
        DriverManager.getDriver().get("https://react-shopping-cart-67954.firebaseapp.com/");
        Waiters.wait(3000);
        List<WebElement> addToCartBtn = DriverManager.getDriver().findElements(By.xpath("//*[text()='Add to cart']"));
        for (WebElement webElement: addToCartBtn)
        {
            JSExecutorUtils.click(DriverManager.getDriver(), webElement);
        }

    }

}
