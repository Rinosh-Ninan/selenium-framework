package com.rinosh.automation.pages;

import com.rinosh.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By logoutBtn = By.xpath("//a[contains(@class,'button') and contains(@href,'logout')]");
    private By loginSuccessMsg = By.xpath("//div[@class='flash success']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void login(String uname, String pword){
        type(username, uname);
        type(password, pword);
        click(loginBtn);
    }

    public String getSuccessMsg(){
        return getText(loginSuccessMsg);
    }

    public void logout(){
        click(logoutBtn);
        waitForVisible(loginBtn);
    }

}
