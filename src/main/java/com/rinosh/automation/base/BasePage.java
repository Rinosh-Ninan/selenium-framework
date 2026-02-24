package com.rinosh.automation.base;

import com.rinosh.automation.exceptions.ElementNotClickableException;
import com.rinosh.automation.exceptions.ElementNotFoundException;
import com.rinosh.automation.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger log =
            LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisible(By locator) {
        try {
            log.debug("Waiting for visibility of element: {}", locator);
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );
        } catch (TimeoutException e) {
            log.error("Unable to find element: {}", locator);
            throw new ElementNotFoundException(
                    "Element not visible after wait: " + locator
            );
        }
    }

    protected void click(By locator) {
        try {
            log.info("Clicking element: {}", locator);
            wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            ).click();
        } catch (TimeoutException e) {
            log.error("Element not clickable after wait: {}", locator);
            throw new ElementNotClickableException(
                    "Element not clickable after wait: " + locator
            );
        } catch (Exception e) {
            throw new FrameworkException(
                    "Unexpected error while clicking: " + locator
            );
        }
    }

    protected void type(By locator, String text) {
        try {
            log.info("Typing into element: {}", locator);
            WebElement element = waitForVisible(locator);
            element.clear();
            element.sendKeys(text);
        } catch (FrameworkException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unable to type into element: {}", locator);
            throw new ElementNotFoundException(
                    "Unable to type into element: " + locator
            );
        }
    }

    protected String getText(By locator) {
        try {
            log.info("Getting text from element: {}", locator);
            return waitForVisible(locator).getText();
        } catch (FrameworkException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unable to get text from element: {}", locator);
            throw new ElementNotFoundException(
                    "Unable to get text from element: " + locator
            );
        }
    }
}