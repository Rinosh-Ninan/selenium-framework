package com.rinosh.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getExtent(){
        if (extent == null){
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report.html");
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Execution Report");
            reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // 🔹 System / Environment info
            extent.setSystemInfo("Project", "Automation Framework");
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.get("baseUrl"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
