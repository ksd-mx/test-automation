package main.java.com.framework.test.selenium;

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
                System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
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
                System.setProperty("webdriver.gecko.driver", "src//main//resources//geckodriver.exe");
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
