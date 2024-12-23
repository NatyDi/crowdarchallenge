package com.crowdar;

import com.crowdar.Utils.ElementFinder;
import com.crowdar.page.LoginPage;
import com.crowdar.page.ShoppingCarPage;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected WebDriver driver;
    protected JavascriptExecutor js;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected FluentWait<WebDriver> fluentWait;
    protected ElementFinder elementFinder;
    protected Dotenv dotenv;
    protected LoginPage loginPage;


    public void setUp(String browser) {
        dotenv = Dotenv.configure()
                .ignoreIfMissing() // que no falle si el archivo .env no existe
                .load();

        if (browser == "chrome") {
            setupChromeDriver();
        } else {
            setupEdgeDriver();
        }


        js = (JavascriptExecutor) driver;

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(100))  // Tiempo máximo de espera
                .pollingEvery(Duration.ofMillis(20))  // Revisión cada 500 ms
                .ignoring(NoSuchElementException.class);  // Ignorar la excepción si el elemento no está presente de inmediato


        elementFinder = new ElementFinder(fluentWait);
        loginPage = new LoginPage(elementFinder, js);

    }

    private void setupEdgeDriver() {
        // Inicializa el navegador Edge
        String userHome = System.getProperty("user.home");
        String driverPath = userHome + File.separator + "drivers" + File.separator + "msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", driverPath);
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    private void setupChromeDriver() {
        // Configuraciones de Chrome
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true); // Evitar advertencias de seguridad

        try {
            logger.info("Configurando Webdriver modo visible");
            String userHome = System.getProperty("user.home");
            String chromeDriverPath = dotenv.get("CHROME_DRIVER_PATH");
            String driverPath;
            if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                logger.info("CHROME_DRIVER_PATH not defined. Using default path.");
                driverPath = userHome + File.separator + "drivers" + File.separator + "chromedriver.exe";
            } else {
                // Use the path defined in .env
                driverPath = chromeDriverPath + File.separator + "chromedriver.exe";
                logger.info("Using CHROME_DRIVER_PATH from .env: " + driverPath);
            }
            System.setProperty("webdriver.chrome.driver", driverPath);

            driver = new ChromeDriver();

            logger.info("Webdriver configurado exitosamente");

        } catch (Exception ex) {
            logger.error("No se pudo cargar ChromeDriver: " + ex.getMessage(), ex);
        }
    }

    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }

    public void iniciodeSesion(String user, String password) {
        iniciodeSesion(user, password, false);
    }

    public void iniciodeSesion(String user, String password, boolean async) {

        logger.info("Abriendo pagina...");

        // Leer la URL base desde el archivo .env
        String baseUrl = dotenv.get("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            logger.error("La variable BASE_URL no está definida en el archivo .env");
            return;
        }

        driver.get(baseUrl);
        driver.manage().window().maximize();

        logger.info("Iniciando sesión...");
        loginPage.login(user, password, async);


        // Espera hasta que cargue el dashboard
        logger.info("Esperando la carga del dashboard");
        loginPage.waitHomePage();

        logger.info("Inicio de sesion finalizado");


    }

}

