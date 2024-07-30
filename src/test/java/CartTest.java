import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class CartTest {

    private static final Logger LOGGER = LogManager.getLogger(CartTest.class);

    WebDriver driver;

    @BeforeMethod
    public  void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/maryiasahanovich/Drivers/chromedriver");

        driver = new ChromeDriver();
    }

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

    @AfterMethod
    public void closeSession() {
        driver.quit();
    }
}
