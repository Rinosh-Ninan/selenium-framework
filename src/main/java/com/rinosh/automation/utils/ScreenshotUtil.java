package com.rinosh.automation.utils;

import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil {
    private static final Logger log =
            LogManager.getLogger(ScreenshotUtil.class);

    public static String capture(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "target/screenshots/" + testName + ".png";
            File dest = new File(path);

            dest.getParentFile().mkdirs();
            Files.copy(src, dest);
            return path;
        } catch (Exception e) {
            log.error("Screenshot capture failed");
            return null;
        }
    }
}
