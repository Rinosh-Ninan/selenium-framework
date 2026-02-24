package com.rinosh.automation.tests;

import com.rinosh.automation.utils.ConfigReader;
import com.rinosh.automation.utils.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    private static final Logger log =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context){
        log.info("Starting test setup");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        log.info("Launching application: {}", ConfigReader.getBaseUrl());
        driver.get(ConfigReader.getBaseUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.get("timeout"))));
        driver.manage().window().maximize();
        // expose driver to TestNG context
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        log.info("Closing browser");
        DriverFactory.quitDriver();
    }
}
