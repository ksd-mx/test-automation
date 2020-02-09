package main.java.com.framework.test.automation.selenium;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
    public static WebDriver createDriver(DriverType driverType) throws WebDriverException {
        WebDriver result;

        switch (driverType) {
            case CHROME:
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
                result = new ChromeDriver(options);

                break;
            case FIREFOX:
                result = new FirefoxDriver();

                break;
            default:
                throw new WebDriverException("Invalid Web Driver Type Configuration");
        }

        return result;
    }

    public enum DriverType {
        CHROME,
        FIREFOX
    }
}
