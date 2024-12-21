package com.crowdar;

import com.crowdar.Utils.ElementFinder;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    public void setUp() {
        dotenv = Dotenv.configure()
                .ignoreIfMissing() // que no falle si el archivo .env no existe
                .load();

        // Configuraciones de Chrome
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true); // Evitar advertencias de seguridad
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        try {
            boolean chromeVisible = Boolean.parseBoolean(dotenv.get("CHROME_VISIBLE"));
            if (!chromeVisible) {
                logger.info("Configurando Webdriver modo invisible");

                // URL del Selenium Grid Hub
                URL hubUrl = new URL("http://localhost:4444");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("127.0");
                capabilities.setPlatform(Platform.LINUX);
                capabilities.merge(options); // Mezclar las opciones con las capacidades
                // Crear la instancia de WebDriver usando RemoteWebDriver
                driver = new RemoteWebDriver(hubUrl, capabilities);
            } else {
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

                driver = new ChromeDriver(options);
            }
            logger.info("Webdriver configurado exitosamente");

        } catch (Exception ex) {
            logger.error("No se pudo cargar ChromeDriver: " + ex.getMessage(), ex);
        }

        js = (JavascriptExecutor) driver;

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))  // Tiempo máximo de espera
                .pollingEvery(Duration.ofMillis(500))  // Revisión cada 500 ms
                .ignoring(NoSuchElementException.class);  // Ignorar la excepción si el elemento no está presente de inmediato
        elementFinder = new ElementFinder(fluentWait);
    }

    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }

}

