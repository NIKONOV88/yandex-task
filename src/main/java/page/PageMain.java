package page;

import core.Property;
import core.WebDriverManager;
import core.YandexTaskException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Класс объединяющий методы общие для всех страниц.
 */
@Slf4j
public abstract class PageMain {

    private final static Long DEFAULT_ELEMENT_TIMEOUT = Long.parseLong(Property.getValue("driver.wait.timeOutInSeconds"));
    private final static Long SLEEP_AFTER_TYPE = Long.parseLong(Property.getValue("driver.wait.SleepAfterTypeInInMillis"));
    protected String namePage;
    protected WebDriver driver = WebDriverManager.getDriver();
    private WebDriverWait driverWait = new WebDriverWait(driver, DEFAULT_ELEMENT_TIMEOUT);

    protected PageMain(String namePage) {
        this.namePage = namePage;
    }

    /**
     * Получить WebElement после его видимости
     *
     * @param locator By
     * @return WebElement
     */
    protected WebElement waitVisibility(By locator) {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Получить список WebElement после видимости первого WebElement из найденных.
     *
     * @param locator By
     * @return список WebElement
     */
    protected List<WebElement> waitVisibilityList(By locator) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    /**
     * Ввести тектс в поле
     *
     * @param nameField название поля
     * @param locator   By
     * @param text      текст
     */
    protected void sendKeys(String nameField, By locator, String text) {
        log.info("{} Ввести текст: \"{}\" в поле: {}", namePage, text, nameField);
        try {
            WebElement el = waitVisibility(locator);
            el.clear();
            el.sendKeys(text);
            sleep(SLEEP_AFTER_TYPE);
        } catch (Exception e) {
            throw new YandexTaskException(namePage + " Ошибка при попытке ввести текст: " + text, e);
        }

    }

    protected void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new YandexTaskException("Ошибка во время таймаута: " + timeout, e);
        }
    }
}
