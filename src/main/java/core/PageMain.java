package core;

import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageMain {

    private final static Long DEFAULT_ELEMENT_TIMEOUT = Long.parseLong(Property.getValue("driver.wait.timeOutInSeconds"));
    protected String name = "no name";
    @LazyInit
    protected WebDriver driver = WebDriverManager.driver;
    @LazyInit
    private WebDriverWait driverWait = new WebDriverWait(driver, DEFAULT_ELEMENT_TIMEOUT);

    protected PageMain(String name) {
        this.name = name;
    }

    protected WebElement waitVisibility(By locator){
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitToBeClickable(By locator){
        return driverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected Boolean waitCondition(ExpectedCondition<WebElement> condition){
        try {
            driverWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
