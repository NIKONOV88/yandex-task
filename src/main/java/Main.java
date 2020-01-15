import core.WebDriverManager;
import page.YandexPage;

public class Main {
    static YandexPage yandexPage = new YandexPage();

    public static void main(String[] args) {
        try {
            yandexPage.open();
            yandexPage.request("по");
            yandexPage.getOfferedItems();
            yandexPage.request("п");
            yandexPage.getOfferedItems();
            yandexPage.request("погодаhhhhhhg");
            yandexPage.getOfferedItems();
            WebDriverManager.quitDriver();
        } finally {
            WebDriverManager.quitDriver();
        }
    }
}
