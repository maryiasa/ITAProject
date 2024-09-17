package com.itacademy.web;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @Test
    public  void googleTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
        open("http://www.google.com");
        $(By.xpath("//*[@id=\"W0wltc\"]/div[@class = 'QS5gu sy4vM']")).click();
        SelenideElement searchInput = $(By.className("gLFyf"));
        searchInput.val("Selenium");
        searchInput.submit();
        $(byText("https://www.selenium.dev")).click();
    }
}
