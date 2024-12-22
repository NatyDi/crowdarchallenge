package com.crowdar.page;
import com.crowdar.Utils.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ShoppingCarPage {
    private final ElementFinder elementFinder;


    //Localizadores de elementos
    //private By addToArticulCar = By.xpath("  //button[@id='add-to-cart-sauce-labs-backpack']");
    private By butonToAddAArticuloToCar = By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    private By butonSecondToAddAArticuloToCar = By.xpath("(//button[contains(text(),'Add to cart')])[2]");
    private By butonMenu = By.xpath("//button[@id='react-burger-menu-btn']");
    private By butonClearCart = By.xpath("//a[@id='reset_sidebar_link']");
    private By textArticuloVisibe = By.xpath("//span[@class='shopping_cart_badge']");
   private By removeCarTArticul = By.xpath("(//button[contains(text(),'Remove')])[1]");

    public ShoppingCarPage(ElementFinder elementFinder) {
        this.elementFinder = elementFinder;
    }


    public void addProductToCart (){
        WebElement butonAddProductToCart = elementFinder.findElement(butonToAddAArticuloToCar);
        butonAddProductToCart.click();

    }


    public void addSecondProductToCart () {
        WebElement butonSecondProductToCart = elementFinder.findElement(butonSecondToAddAArticuloToCar);
        butonSecondProductToCart.click();
    }

    public void menuCart () {
        WebElement butonMenuCart = elementFinder.findElement(butonMenu);
        butonMenuCart.click();
    }

    public boolean isProductsCartVisible() {
        try {
            WebElement productsElement = elementFinder.findElement(textArticuloVisibe);
            return productsElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void resetCart () {
        WebElement butonResetCart = elementFinder.findElement(butonClearCart);
        butonResetCart.click();
    }

    public void removeCartArti () {
        WebElement butonRemoveCart = elementFinder.findElement(removeCarTArticul);
        butonRemoveCart.click();
    }
}







