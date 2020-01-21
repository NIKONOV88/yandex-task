import core.WebDriverManager;
import page.YandexPage;

/**
 * Класс реализует выполнение задачи №1
 */
public class Task1 {
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
