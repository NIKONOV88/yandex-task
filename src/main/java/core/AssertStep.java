package core;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.util.Collection;

@Slf4j
public class AssertStep {


    public static <T> void assertContains(String message, Collection<T> collection, T item) {
        assertTrue(message, collection.contains(item));
    }

    @Step("Проверка, {message}")
    public static <T> void assertEquals(String message, T actual, T expected) {
        log.info("Проверка, {} \n\tактуальное значение: {}, \n\tожидаемое значение:  {}", message, actual, expected);
        Assertions.assertEquals(actual, expected, "Ошибка при проверке. " + message);
    }

    @Step("Проверка, {message}")
    public static void assertTrue(String message, Boolean condition) {
        log.info("Проверка, {}", message);
        Assertions.assertTrue(condition, "Ошибка при проверке. " + message);
    }
}
