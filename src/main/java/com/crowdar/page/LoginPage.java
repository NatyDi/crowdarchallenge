package com.crowdar.page;

import com.crowdar.Utils.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage {
    private final ElementFinder elementFinder;
    private final JavascriptExecutor jsExecutor;


    // Localizadores de los elementos
    private static final By emailField = By.xpath("//input[@id='user-name']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By messageErrorBlokedUser = By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]");
    private By textProductVisible = By.xpath("//span[@class='title']");

    public LoginPage(ElementFinder elementFinder, JavascriptExecutor jsExecutor) {
        this.elementFinder = elementFinder;
        this.jsExecutor = jsExecutor;
    }

    public void login(String user, String password) {
        login(user, password, false);
    }

    public void login(String user, String password, boolean async) {
        elementFinder.findElement(emailField).sendKeys(user);
        elementFinder.findElement(passwordField).sendKeys(password);
        WebElement boton = elementFinder.findClickableElement(loginButton);
        if (async == false) {
            boton.click();
        } else {
            Thread hiloClick = new Thread(() -> {
                try {
                    boton.click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            hiloClick.start();
        }
       }

    public void waitHomePage() {
    }

    public boolean isHomePageLoaded() {
        return elementFinder.isPageLoaded("/inventory.html");

    }

    public boolean isHomePageLoaded(Duration duration) {
        return elementFinder.isPageLoaded("/inventory.html", duration);

    }

    public boolean isProductsTextVisible() {
        try {
            WebElement productsElement = elementFinder.findElement(textProductVisible);
            return productsElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMessageErrorBlockedUserShown() {
        WebElement element = elementFinder.findElement(messageErrorBlokedUser);
        return (element != null);
    }

    public void waitMillis() {
        elementFinder.waitMillis();
    }



}
