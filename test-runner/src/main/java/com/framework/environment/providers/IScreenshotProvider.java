package main.java.com.framework.environment.providers;

import java.util.stream.Stream;

public interface IScreenshotProvider extends IProvider {

    Stream getScreenshot();

}
