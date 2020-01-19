package page;

import core.YandexTaskException;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Главная страница Яндекс.
 */
@Slf4j
public class YandexPage extends PageMain {

    private static final String URL = "https://yandex.ru/";
    private By requestArea = By.xpath("//input[@aria-label='Запрос']");

    private By firstOfferedItems = By.xpath("(//li[contains(@class,'suggest')])[1]/span");
    private By homeTabsSearch = By.xpath("//div[contains(@class,'home-tabs')]/a[contains(@class,'home-tabs__search')]");
    private String tabXpath = "//div[contains(@class,'home-tabs')]/a[text()='%s']";

    public YandexPage() {
        super("Главная страница Яндекс. ");
    }

    @Step("Открыть страницу: " + URL)
    public YandexPage open() {
        log.info("{}Открыть страницу", namePage);
        driver.get(URL);
        return this;
    }

    /**
     * Ввести текст в строку запроса
     *
     * @param text
     * @return
     */
    public YandexPage typeRequest(String text) {
        sendKeys("строка запроса", requestArea, text);
        return this;
    }

    /**
     * Получить текст первого элемента выпадающего списка с контекстным поиском
     *
     * @return текст первого элемента выпадающего списка с контекстным поиском
     */
    public String getFirstOrderedItem() {
        log.info("{}Получить текст первого элемента выпадающего списка с контекстным поиском.", namePage);
        List<WebElement> el = null;
        try {
            el = waitVisibilityList(firstOfferedItems);
        } catch (Exception e) {
            log.error("{}Ошибка при получениии текста первого элемента выпадающего списка с контекстным поиском. Список отсутствует.", namePage);
            return null;
//            throw new YandexTaskException(namePage + "Ошибка при получениии текста первого элемента выпадающего списка с контекстным поиском. Список отсутствует.");
        }
        List<String> list = el.stream()
                .map(e -> parseSpan(e))
                .collect(toList());
        String text = String.join(" ", list);
        log.info("{}Получен текст первого элемента выпадающего списка с контекстным поиском: \n\t{}", namePage, text);
        return String.join(" ", list);
    }

    public List<String> getHomeTabsSearch() {
        return waitVisibilityList(homeTabsSearch).stream()
                .map(WebElement::getText)
                .collect(toList());
    }

    public int getNumberTab(String nameTab) {
        List<String> tabs = getHomeTabsSearch();
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).equals(nameTab)) {
                return i;
            }
        }
        throw new YandexTaskException(namePage + "Таб: " + nameTab + " не найден на основной панели");
    }

    public boolean isTabEnabled(String nameTab) {
        return waitVisibility(By.xpath(String.format(tabXpath, nameTab))).isEnabled();
    }

    /**
     * Парсинг дочернего элемента (/span) выпадающего списка с контекстным поиском
     *
     * @param el child(/span) элемента овыпадающего списка с контекстным поиском
     * @return текст части элемента выпадающего списка с контекстным поиском
     */
    private String parseSpan(WebElement el) {
        if (el.getAttribute("class").contains("item__icon")) {
            String weather = parseWeatherPicture(el.getAttribute("style"));
            if (weather == null) {
                return "unknown picture";
            }
            return weather;
        }
        return el.getText();
    }

    /**
     * Парсинг картинки с погодой
     *
     * @param style значение атрибута style
     * @return описание погоды в формате String
     */
    private String parseWeatherPicture(String style) {
        String prefix = "icon.weather.";
        String suffix = ".svg";
        if (!(style.contains(prefix) && style.contains(suffix))) {
            return null;
        }
        int startIndex = style.indexOf(prefix) + prefix.length();
        int endIndex = style.indexOf(suffix);
        List<String> comboWeatherList = Arrays.stream(style
                .substring(startIndex, endIndex)
                .split("-"))
                .map(sign -> Weather.getDescriptionBySign(sign))
                .filter(Objects::nonNull)
                .collect(toList());

        if (comboWeatherList.isEmpty()) {
            return null;
        }
        return String.join(" ", comboWeatherList);
    }

    /**
     * Описание погоды зашифрованной в названии картинки
     */
    @AllArgsConstructor
    @Getter
    private enum Weather {
        CLOUDY("bkn", "облачно"),
        LIGHT("minus", "небольшой"),
        HEAVY("plus", "сильный"),
        SNOW("sn", "снег"),
        RAIN("ra", "дождь"),
        MAINLY_CLOUDY("ovc", "пасмурно"),
        THUNDER("ts", "гроза"),
        WINDY("wnd", "ветренно"),
        CLEAR("skc", "ясно");

        String sign;
        String description;

        static String getDescriptionBySign(String sign) {
            return Arrays.stream(values())
                    .filter(w -> w.sign.equals(sign))
                    .map(Weather::getDescription)
                    .findAny()
                    .orElse(null);
        }
    }
}
