package com.crowdar.page;

import com.crowdar.Utils.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final ElementFinder elementFinder;



    // Localizadores de los elementos
    private static final By emailField = By.xpath("//input[@id='user-name']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By messageErrorBlokedUser = By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]");

    public LoginPage(ElementFinder elementFinder) {
        this.elementFinder = elementFinder;
    }

    public void login(String user, String password) {
        // se escribe el usurname
        // se escribe el password
        // se hace clic en el boton login

        elementFinder.findElement(emailField).sendKeys(user);
        elementFinder.findElement(passwordField).sendKeys(password);
        elementFinder.findClickableElement(loginButton).click();
    }


    public void waitHomePage() {
    }

    public boolean isHomePageLoaded() {
        return elementFinder.isPageLoaded("/inventory.html");

    }

    public boolean isMessageErrorBlockedUserShown() {
        WebElement element = elementFinder.findElement(messageErrorBlokedUser);
        return (element != null);
    }


}
