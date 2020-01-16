import core.WebDriverManager;
import page.YandexPage;

public class Main {
    static YandexPage yandexPage = new YandexPage();

    public static void main(String[] args) {
        try {
            yandexPage.open();
            yandexPage.request("погода р");
            System.out.println(yandexPage.get());
            yandexPage.request("пого");
            System.out.println(yandexPage.getFirstOfferedItem());
            yandexPage.request("погода");
            System.out.println(yandexPage.getFirstOfferedItem());
        } finally {
            WebDriverManager.quitDriver();
        }
    }
}
