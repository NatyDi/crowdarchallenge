package com.crowdar;

import com.crowdar.Utils.ElementFinder;
import com.crowdar.page.ShoppingCarPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ShopingCartTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ShopingCartTest.class);
    ShoppingCarPage shoppingCarPage;

    @BeforeClass
    public void setUp() {
        super.setUp();
        shoppingCarPage = new ShoppingCarPage(super.elementFinder);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }

    @Test (priority = 2)
    public void addFirstArticulCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.addProductToCart();
    }

    @Test (priority = 3)
    public void addSecondArticulCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.addSecondProductToCart();
    }


    @Test (priority = 4)
    public void removeCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.removeCartArti();

    }

    @Test (priority = 1)
    public void clearShoppingCar() {

        //Iniciar sesión antes de realizar la prueba
        super.iniciodeSesion("standard_user", "secret_sauce");

        logger.info("Haciendo clic en el boton de menu..");
        shoppingCarPage.menuCart();

        elementFinder.waitMillis();

        logger.info("Haciendo clic en el boton de reset..");
        shoppingCarPage.resetCart();

        // Verificar que la página no tenga articulos
        logger.info("verificar que no haya articulos en el carrito");
        Assert.assertFalse(shoppingCarPage.isProductsCartVisible(), "Los articulos no se encontraron en el carrito");
        logger.info("Prueba finalizada con éxito");



    }


}

