package com.crowdar.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class ElementFinder {

    private final FluentWait<WebDriver> fluentWait;

    public ElementFinder(FluentWait<WebDriver> fluentWait) {
        this.fluentWait = fluentWait;
    }

    public WebElement findElement(By selector) {

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement findClickableElement(By selector) {

        return fluentWait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public boolean isPageLoaded(String page) {
        return fluentWait.until(ExpectedConditions.urlContains(page));
    }

    public boolean isPageLoaded(String page, Duration timeout) {
        // Configure a custom FluentWait for the specified timeout
        FluentWait<WebDriver> customWait = fluentWait.withTimeout(timeout);
        return customWait.until(ExpectedConditions.urlContains(page));
    }

    // Método para encontrar múltiples elementos
    public List<WebElement> findElements(By selector) {

        return fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    public void waitMillis() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

