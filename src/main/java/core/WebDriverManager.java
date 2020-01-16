package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private WebDriverManager() {}

    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver77.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        } else {
            return driver;
        }
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
        }
    }
}
