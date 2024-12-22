package com.crowdar.chrome;

import com.crowdar.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTest  extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);


    @BeforeClass
    public void setUp() {
        super.setUp("chrome");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }

    @Test
    public void iniciodeSesionstandar_user() {
        super.iniciodeSesion("standard_user", "secret_sauce");

        logger.info("Verificando que la página de Home ha cargado...");
        //super.loginPage.waitMillis();
        Assert.assertTrue(super.loginPage.isHomePageLoaded(), "La página de Home no se cargó correctamente");
        Assert.assertTrue(super.loginPage.isProductsTextVisible(), "La palabra 'Products' no se encontró en la página");
        logger.info("Prueba finalizada con éxito");
    }
    @Test
    public void iniciodeSesionlocked_out_user() {
        super.iniciodeSesion("locked_out_user", "secret_sauce");
        // hacer assert que el mensaje de error fue mostrado para el usuario bloqueado
        Assert.assertTrue(super.loginPage.isMessageErrorBlockedUserShown(), "El mensaje de Error no fue mostrado");
        logger.info("Prueba finalizada con éxito");
    }
    @Test
    public void problem_user() {
        super.iniciodeSesion("problem_user", "secret_sauce");

        logger.info("Verificando que la página de Home ha cargado...");
        Assert.assertTrue(super.loginPage.isHomePageLoaded(), "La página de Home no se cargó correctamente");
        Assert.assertTrue(super.loginPage.isProductsTextVisible(), "La palabra 'Products' no se encontró en la página");
        logger.info("Prueba finalizada con éxito");
    }

    @Test
    public void performance_glitch_user() {
        super.iniciodeSesion("performance_glitch_user", "secret_sauce", true);
        logger.info("Verificando que la página de Home ha cargado...");
        Assert.assertTrue(super.loginPage.isProductsTextVisible(), "La palabra 'Products' no se encontró en la página");
        logger.info("Prueba finalizada con éxito");
    }

    @Test
    public void error_user() {
        super.iniciodeSesion("error_user", "secret_sauce");

        logger.info("Verificando que la página de Home ha cargado...");
        Assert.assertTrue(super.loginPage.isHomePageLoaded(), "La página de Home no se cargó correctamente");
        Assert.assertTrue(super.loginPage.isProductsTextVisible(), "La palabra 'Products' no se encontró en la página");
        logger.info("Prueba finalizada con éxito");
    }
    @Test
    public void visual_user() {
        super.iniciodeSesion("visual_user", "secret_sauce");

        logger.info("Verificando que la página de Home ha cargado...");
        Assert.assertTrue(super.loginPage.isHomePageLoaded(), "La página de Home no se cargó correctamente");
        Assert.assertTrue(super.loginPage.isProductsTextVisible(), "La palabra 'Products' no se encontró en la página");
        logger.info("Prueba finalizada con éxito");
    }


}

