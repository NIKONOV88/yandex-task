package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static core.WebDriverManager.getDriver;

public class YandexPage {

    public static final String NAME = "Главная страница Яндекс";
    private By requestArea = By.xpath("//input[@aria-label='Запрос']");

    public void open() {
        getDriver().get("https://yandex.ru/");
    }

    public void request(String text) {
        System.out.println("Вводим текст: " + text);
        WebElement queryArea = getDriver().findElement(requestArea);
        queryArea.clear();
        queryArea.sendKeys(text);
    }

    public List<String> getOfferedItems() {
        List<String> result = getDriver().findElements(By.xpath("//li[contains(@class,'suggest')]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println(result);
        System.out.println("-----------------------");
        return result;
    }

    public List<String> getOfferedItems2() {
        List<String> result = getDriver().findElements(By.xpath("//div[@class='popup__content']//li/descendant::*"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println(result);
        System.out.println("-----------------------");
        return result;
    }
}
