package main.framework.test.selenium;

import main.framework.configuration.ConfigurationManager;
import main.framework.configuration.model.ExecutionSettings;
import main.framework.services.DefaultLocalService;
import main.framework.test.mappings.WebElementMapping;
import main.framework.test.model.TestStep;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WebDriverManager extends DefaultLocalService {
    public static final String DRIVER_TYPE = "webdriver.chrome.driver";
    public static final String SERVICE_KEY = "webdriver.manager";

    private final ConfigurationManager configurationManager;
    private final WebDriver driver;

    public WebDriverManager(ConfigurationManager configurationManager) {
        super(configurationManager.getContextId());
        String driverFile =
                String.format(
                        "src//main//resources//chromedriver%s",
                        System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "");

        this.configurationManager = configurationManager;

        switch (configurationManager
                .getExecutionSettings()
                .getDriverType()) {
            case CHROME:
                System.setProperty(DRIVER_TYPE, driverFile);
                DesiredCapabilities dr = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(
                        "--disable-gpu",
                        "--ignore-certificate-errors",
                        "--silent",
                        "--disable-translate",
                        "--disable-extensions",
                        "--disable-component-extensions-with-background-pages",
                        "--disable-popup-blocking",
                        "--disable-web-security");
                dr.setJavascriptEnabled(true);
                options.merge(dr);
                this.driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src//main//resources//geckodriver.exe");
                this.driver = new FirefoxDriver();
                break;
            default:
                throw new WebDriverException("Invalid Web Driver Type Configuration");
        }
    }

    @Override
    public void initialize() {
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        this.driver.quit();
    }

    public void pause() {
        this.pause(this.configurationManager.getExecutionSettings().getRetryInterval());
    }

    public void pause(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateTo() {
        this.navigateTo(null, null);
    }

    public void navigateTo(String url){
        this.driver.navigate().to(url);
    }

    public void navigateTo(String key, String credentialKey) {
        String pageAddress = this.configurationManager.getExecutionSettings().getPageAddress(key, credentialKey);

        this.navigateTo(pageAddress);
    }

    public void switchTo() {
        this.switchTo(null);
    }

    public void switchTo(WebElement element) {
        if (element != null)
            this.driver.switchTo().frame(element);
        else
            this.driver.switchTo().defaultContent();

        this.pause();
    }

    public boolean ensurePageLoaded() {
        return this.waitFor((driver) ->
                this.executeScript("return document.readyState")
                        .equals("complete"));
    }

    Object executeScript(String var1, Object... var2) {
        return ((JavascriptExecutor)this.driver).executeScript(var1, var2);
    }

    Object executeAsyncScript(String var1, Object... var2) {
        return ((JavascriptExecutor)this.driver).executeAsyncScript(var1, var2);
    }

    public WebElementHandler getWebElement(String elementKey) {
        return this.getWebElement(elementKey, null);
    }

    public WebElementHandler getWebElement(String elementKey, String valueKey) {
        List<WebElementHandler> result = this.getWebElements(elementKey, valueKey,null);

        return result.get(0);
    }

    public List<WebElementHandler> getWebElements(String elementKey, String valueKey) {
        return this.getWebElements(elementKey, valueKey, null);
    }

    public List<WebElementHandler> getWebElements(String elementKey, String valueKey, String childrenSelectorKey) {
        WebElementMapping mapping = this.configurationManager
                .getWebElementMappingCollection()
                .get(elementKey);

        WebElement mainElement = this.driver.findElement(By.xpath(mapping.getPath()));

        if (childrenSelectorKey != null) {
            WebElementMapping selectorMapping = this.configurationManager
                    .getWebElementMappingCollection()
                    .get(childrenSelectorKey);

            return mainElement
                    .findElements(By.xpath(selectorMapping.getPath()))
                    .stream()
                    .map((item) -> new WebElementHandler(this, selectorMapping, item, valueKey))
                    .collect(Collectors.toList());
        }

        return mainElement
                .findElements(By.xpath(mapping.getPath()))
                .stream()
                .map((item) -> new WebElementHandler(this, mapping, mainElement, valueKey))
                .collect(Collectors.toList());
    }

    public <T> T waitFor(ExpectedCondition<T> condition) {
        ExecutionSettings settings = this.configurationManager.getExecutionSettings();
        int waitTimeout = settings.getWaitTimeout();
        int retryInterval = settings.getRetryInterval();

        return this.waitFor(condition, waitTimeout, retryInterval);
    }

    public <T> T waitFor(ExpectedCondition<T> condition, boolean noRetry) {
        int waitTimeout = this.configurationManager.getExecutionSettings().getWaitTimeout();

        return this.waitFor(condition, waitTimeout, noRetry);
    }

    public <T> T waitFor(ExpectedCondition<T> condition, int timeOut, boolean noRetry) {
        int retryInterval = this.configurationManager.getExecutionSettings().getRetryInterval();

        return this.waitFor(condition, timeOut, retryInterval, noRetry);
    }

    public <T> T waitFor(ExpectedCondition<T> condition, int timeOut, int interval) {
        return this.waitFor(condition, timeOut, interval, false);
    }

    public <T> T waitFor(ExpectedCondition<T> condition, int timeOut, int interval, boolean noRetry) {
        int retryCount = this.configurationManager.getExecutionSettings().getRetryCount();
        WebDriverWait wait = new WebDriverWait(this.driver, timeOut);
        T result = null;
        while (retryCount > 0) {
            result = wait.until(condition);
            if (noRetry) return result;
            if (result instanceof Boolean && ((Boolean)result)) return result;
            if (interval > 0) this.pause(interval);
            retryCount--;
        }
        return result;
    }

    public void takeScreenshot(TestStep testStep, String suffix) throws IOException {
        Path filePath =
                Paths.get(
                        ExecutionSettings.RESULT_FILEPATH,
                        this.getContextId(),
                        testStep.getTestCase().getExternalId(),
                        testStep.getExternalId(),
                        String.format("SCREENSHOT_%s_%s_%s.png",
                                testStep.getTestCase().getExternalId(),
                                testStep.getExternalId(),
                                suffix));

        System.out.println(String.format("Saving screenshot to %s", filePath.toString()));

        File screenshotFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        File result = new File(filePath.toString());
        // Moving the newly generated screenshot image
        // into the results folder. There should be no other
        // repeated files so no deletions are necessary.
        FileUtils.moveFile(screenshotFile, result);
        testStep.getScreenshotList().add(filePath.toString());
    }

    public enum DriverType {
        CHROME,
        FIREFOX
    }
}
