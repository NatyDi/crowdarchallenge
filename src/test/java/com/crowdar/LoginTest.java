package com.crowdar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest  extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setUp() {
        super.setUp();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }

    @Test
    public void iniciodeSesionstandar_user() {
        super.iniciodeSesion("standard_user", "secret_sauce");

        logger.info("Verificando que la página de Home ha cargado...");
        softAssert.assertTrue(super.loginPage.isHomePageLoaded(), "La página de Home no se cargó correctamente");
        logger.info("Prueba finalizada con éxito");
    }
    @Test
    public void iniciodeSesionlocked_out_user() {
        super.iniciodeSesion("locked_out_user", "secret_sauce");
        // hacer assert que el mensaje de error fue mostrado para el usuario bloqueado
        softAssert.assertTrue(super.loginPage.isMessageErrorBlockedUserShown(), "El mensaje de Error no fue mostrado");
        logger.info("Prueba finalizada con éxito");
    }
}

