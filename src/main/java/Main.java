import core.WebDriverManager;
import page.YandexPage;

public class Main {
    static YandexPage yandexPage = new YandexPage();

    public static void main(String[] args) {
        try {
            yandexPage.open();
            yandexPage.request("пого");
            System.out.println(yandexPage.get());
            yandexPage.request("погода р");
            System.out.println(yandexPage.get());
            yandexPage.request("погода");
            System.out.println(yandexPage.get());
            yandexPage.request("погодаdfdsgbhgrt rtyhsrt ts rs tsert ");
            System.out.println(yandexPage.get());
        } finally {
            WebDriverManager.quitDriver();
        }
    }
}
