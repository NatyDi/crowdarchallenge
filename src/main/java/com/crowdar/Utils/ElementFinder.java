package com.crowdar.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class ElementFinder {

    private final FluentWait<WebDriver> fluentWait;

    public ElementFinder(FluentWait<WebDriver> fluentWait) {
        this.fluentWait = fluentWait;
    }

    public WebElement findElement(By selector) {
        waitMillis();
        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement findClickableElement(By selector) {
        waitMillis();
        return fluentWait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public boolean isPageLoaded(String page) {
        return fluentWait.until(ExpectedConditions.urlContains(page));
    }

    // Método para encontrar múltiples elementos
    public List<WebElement> findElements(By selector) {
        waitMillis();
        return fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selector));
    }

    public void waitMillis() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

