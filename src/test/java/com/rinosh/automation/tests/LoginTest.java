package com.rinosh.automation.tests;

import com.rinosh.automation.listeners.RetryAnalyzer;
import com.rinosh.automation.pages.LoginPage;
import com.rinosh.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test(
            groups = {"smoke"},
            retryAnalyzer = RetryAnalyzer.class
    )
    public void loginTest() {
        WebDriver driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");
        String loginSuccessMsg = loginPage.getSuccessMsg();
        Assert.assertTrue(loginSuccessMsg.contains("You logged into a secure area!"), "Login success message not displayed");
    }

    @Test(
            groups = {"regression"},
            retryAnalyzer = RetryAnalyzer.class
    )
    public void logoutTest() {
        WebDriver driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");
        loginPage.logout();
        String logoutSuccessMsg = loginPage.getSuccessMsg();
        Assert.assertTrue(logoutSuccessMsg.contains("You logged out of the secure area!"), "Logout success message not displayed");
    }
}
