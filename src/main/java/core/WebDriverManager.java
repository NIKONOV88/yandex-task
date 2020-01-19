package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Класс отвечающий за работу с WebDriver
 */
@Slf4j
public class WebDriverManager {

    private static final String PATH = Property.getValue("driver.path");
    private static WebDriver driver = null;

    private WebDriverManager() {
    }

    public static boolean isInit() {
        return driver != null;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                log.debug("Открыть браузер. Путь к драйверу: {}", PATH);
                System.setProperty("webdriver.chrome.driver", PATH);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                return driver;
            } catch (Exception e) {
                throw new YandexTaskException("Ошибка при создании драйвера", e);
            }
        } else {
            return driver;
        }
    }

    public static void quitDriver() {
        if (isInit()) {
            log.debug("Завершить работу драйвера");
            driver.quit();
        }
    }
}
