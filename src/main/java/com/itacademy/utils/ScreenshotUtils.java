package com.itacademy.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class ScreenshotUtils {
    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);

    public static void  makeScreenshot(WebDriver driver) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDateStr = simpleDateFormat.format(date);
        File file1 = new  File("screenshot" + currentDateStr + ".png");
        try {
            FileUtils.copyFile(file, file1);
        } catch (IOException e) {
            log.error("Screenshot was not saved");
        }
    }

    public static void  makeScreenshot(WebDriver driver, String filename) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDateStr = simpleDateFormat.format(date);
        File file1 = new  File("screenshots/" + filename + currentDateStr + ".png");
        try {
            FileUtils.copyFile(file, file1);
        } catch (IOException e) {
            log.error("Screenshot was not saved");
        }
    }
}
