package core;

public class YandexTaskException extends RuntimeException {
    public YandexTaskException(String message) {
        super(message);
    }

    public YandexTaskException(String message, Exception e) {
        super(message, e);
    }
}
