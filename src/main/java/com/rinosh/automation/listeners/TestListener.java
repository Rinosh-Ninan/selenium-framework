package com.rinosh.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rinosh.automation.utils.ExtentManager;
import com.rinosh.automation.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("===== TEST SUITE STARTED =====");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestClass().getName() + " : " + result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);

        // 🔹 Add groups as categories
        for (String group : result.getMethod().getGroups()) {
            extentTest.assignCategory(group);
        }
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(
                Status.FAIL,
                result.getThrowable()
        );

        Object driverObj =
                result.getTestContext().getAttribute("driver");

        if (driverObj instanceof WebDriver) {
            String path = ScreenshotUtil.capture((WebDriver) driverObj, result.getName());
            log.info("Screenshot captured for test: {}", result.getName());
            if (path != null) {
                test.get().addScreenCaptureFromPath(path);
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("===== TEST SUITE FINISHED =====");
        extent.flush();
    }
}
