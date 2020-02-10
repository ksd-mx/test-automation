package main.java.com.framework.environment;

import main.java.com.framework.environment.providers.IScreenshotProvider;

import java.io.File;
import java.util.stream.Stream;

public class EnvironmentContext {
    public static String getEnvironmentVariableValue(String keyName) {
        String value = System.getenv("BUILD_BUILDURI");

        if (value == null || value.trim().length() == 0) {
            throw new NullPointerException("Invalid Environment Variable Key Name: " + keyName);
        }

        return value;
    }
}
