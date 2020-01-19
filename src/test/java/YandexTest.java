import core.AssertStep;
import core.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.YandexPage;

@UITest
class YandexTest {

    private static final String TAB_NAME = "Картинки";
    YandexPage yandexPage = new YandexPage();

    @Test
    @DisplayName("Проверить корректное отображение вкладки “Картинки”")
    public void locationTabPicturesTest() {
        yandexPage.open();
        AssertStep.assertContains("Основная панель содержит вкладку: Картинки", yandexPage.getHomeTabsSearch(), TAB_NAME);
        AssertStep.assertEquals("Порядковый номер вкладки Картинки", yandexPage.getNumberTab(TAB_NAME), 1);
        AssertStep.assertTrue("Вкладка Картинки активна", yandexPage.isTabEnabled(TAB_NAME));
    }
}
