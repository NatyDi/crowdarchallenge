package com.crowdar.edge;

import com.crowdar.BaseTest;
import com.crowdar.Utils.CapturaEvidencia;
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
        super.setUp("edge");
        shoppingCarPage = new ShoppingCarPage(super.elementFinder);
        //Iniciar sesión antes de realizar la prueba
        super.iniciodeSesion("standard_user", "secret_sauce");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }

    @Test(priority = 2)
    public void addFirstArticulCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.addProductToCart();
    }

    @Test(priority = 3)
    public void addSecondArticulCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.addSecondProductToCart();
    }


    @Test(priority = 4)
    public void removeCar() {
        logger.info("Haciendo clic en el boton...");
        shoppingCarPage.removeCartArti();

    }

    @Test(priority = 1)
    public void clearShoppingCar() {


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

    @Test(priority = 5)
    public void clearShoppingCarConFallaIntencional() throws Exception {
        try {
            logger.info("Haciendo clic en el boton de menu..");
            shoppingCarPage.menuCart();

            elementFinder.waitMillis();

            logger.info("Haciendo clic en el boton de reset..");
            shoppingCarPage.resetCart();

            // Verificar que la página no tenga articulos
            logger.info("verificar que no haya articulos en el carrito");
            Assert.assertTrue(shoppingCarPage.isProductsCartVisible(), "Los articulos no se encontraron en el carrito");
            logger.info("Prueba finalizada con éxito");
        } catch (Throwable e) {
            CapturaEvidencia.capturaPantallaEnDocumento(super.driver, "Falla Intencional.docx", "Falla Intencional");
            Assert.fail("Falla Intencional", e);
        }
    }

}

