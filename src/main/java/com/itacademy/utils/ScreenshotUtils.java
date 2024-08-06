package com.itacademy.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static void  makeScreenshot(WebDriver driver) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //fix format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDateStr = simpleDateFormat.format(date);
        File file1 = new  File("screenshot" + currentDateStr + ".png");
        try {
            FileUtils.copyFile(file, file1);
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");;
        }
    }

    public static void  makeScreenshot(WebDriver driver, String filename) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDateStr = simpleDateFormat.format(date);
        File file1 = new  File(filename + currentDateStr + ".png");
        try {
            FileUtils.copyFile(file, file1);
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");;
        }
    }
}
