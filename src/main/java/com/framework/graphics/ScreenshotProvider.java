package main.java.com.framework.graphics;

import main.java.com.framework.component.model.DefaultProvider;
import main.java.com.framework.test.selenium.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotProvider extends DefaultProvider {
    public File provide() {
        return ((TakesScreenshot) WebDriverManager.getCurrent().getDriver()).getScreenshotAs(OutputType.FILE);
    }
}