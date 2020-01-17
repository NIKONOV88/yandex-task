package core;

public class YandexTaskException extends RuntimeException {
    YandexTaskException(String message) {
        super(message);
    }

    YandexTaskException(String message, Exception e) {
        super(message, e);
    }
}
