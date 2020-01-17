package page;

import core.PageMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class YandexPage extends PageMain {

    public YandexPage() {
        super("Главная страница Яндекс.");
    }

    private By requestArea = By.xpath("//input[@aria-label='Запрос']");
    private By firstOfferedItem = By.xpath("//input[@aria-label='Запрос']");
    private By searchForm = By.xpath("//form[@aria-label='Поиск в интернете']");

    public void open() {
        driver.get("https://yandex.ru/");
    }

    public YandexPage request(String text) {
        System.out.println(name + " Вводим в строку поиска текст: " + text);
        WebElement queryArea = waitVisibility(requestArea);
        queryArea.clear();
        queryArea.sendKeys(text);
        return this;
    }

    public String getFirstOfferedItem() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(firstOfferedItem).getText();
    }

    public String get() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> el = driver.findElements(By.xpath("(//li[contains(@class,'suggest')])[1]/span"));
        List<String> list = el.stream().map(e -> parse(e)).collect(Collectors.toList());
        return String.join(" ", list);
    }

    public String parse(WebElement el) {
        if (el.getAttribute("class").contains("item__icon")) {
            return el.getAttribute("style");
        }
        return el.getText();
    }

    public List<String> getOfferedItems() {
        List<String> result = driver.findElements(By.xpath("//li[contains(@class,'suggest')]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println(result);
        System.out.println("-----------------------");
        return result;
    }

    public List<String> getOfferedItems2() {
        List<String> result = driver.findElements(By.xpath("//div[@class='popup__content']//li/descendant::*"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println(result);
        System.out.println("-----------------------");
        return result;
    }
}
