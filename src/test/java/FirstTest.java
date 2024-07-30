import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {

    WebDriver driver;

    @BeforeMethod
    public  void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/maryiasahanovich/Drivers/chromedriver");

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest () {


        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");

        WebElement element = driver.findElement(By.name("q"));

        //how to find element
        driver.findElement(By.name("sgdg"));
        driver.findElement(By.id(""));
        driver.findElement(By.className(""));
        driver.findElement(By.cssSelector(""));
        driver.findElement(By.xpath(""));

        element.click(); // click on button
        String txt = element.getText();
        String text = element.getCssValue("color");


        element.sendKeys("Selenium");
        element.submit();

        driver.navigate().back(); // back to the previous page
        driver.navigate().refresh(); //refresh page
    }

    @AfterMethod
    public void closeSession() {
        driver.quit();
    }
}
