import core.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import page.YandexPage;

@Slf4j
public class Main {
    private static YandexPage yandexPage = new YandexPage();


    public static void main(String[] args) {
        try {
            yandexPage.open();
            getFirstOrderedItemBySearch("погода");
            getFirstOrderedItemBySearch("Липецк");
            getFirstOrderedItemBySearch("Лото");

        } finally {
            WebDriverManager.quitDriver();
        }
    }

    static String getFirstOrderedItemBySearch(String text) {
        return yandexPage.typeRequest(text)
                .getFirstOrderedItem();
    }
}
