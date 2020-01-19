package core;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
public class UITestExtension implements TestWatcher, AfterEachCallback, BeforeEachCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        log.warn("END TEST:\t{}", extensionContext.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        log.warn("START TEST:\t{}", extensionContext.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("-----SUCCESS------");
        WebDriverManager.quitDriver();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("-----DISABLED------");
        WebDriverManager.quitDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("-----ABORTED------");
        WebDriverManager.quitDriver();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.error("-----FAILED------");
        if (WebDriverManager.isInit()) {
            makeScreenshotOnFailure();
            WebDriverManager.quitDriver();
        }
    }

    private void makeScreenshotOnFailure() {
        byte[] screenShot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", screenShot);
    }

}
