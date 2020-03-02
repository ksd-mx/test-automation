package main.java.com.framework.test.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private final WebDriver driver;

    public WebDriverManager(DriverFactory.DriverType driverType) {
        this.driver = DriverFactory.createDriver(driverType);
    }

    public void getScreenShot(String filePath) throws Throwable {
        File screenshotFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);

        File result = new File(filePath);

        FileUtils.moveFile(screenshotFile, result);
    }

    public void startWebDriver() {
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void stopWebDriver() {
        this.driver.quit();
    }
}
