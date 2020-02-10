package main.java.com.framework.test.automation.selenium;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static WebDriverManager current;

    // Driver property is final since recreating the driver is not allowed
    private final WebDriver driver;

    private WebDriverManager(DriverFactory.DriverType driverType) {
        this.driver = DriverFactory.createDriver(driverType);
    }

    public static WebDriverManager getCurrent() {
        return getCurrent(DriverFactory.DriverType.CHROME);
    }

    public static WebDriverManager getCurrent(DriverFactory.DriverType driverType) {
        // Not considering whether there is an existing driver
        // from a different type because runtime driver type
        // switching support is not required yet.
        if (WebDriverManager.current == null) {
            WebDriverManager.current = new WebDriverManager(driverType);
        }

        return WebDriverManager.current;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void startWebDriver() {
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void stopWebDriver() {
        this.driver.close();
        this.driver.quit();
    }
}
