package main.java.com.framework.environment;

public class EnvironmentContext {
    public static String getEnvironmentVariableValue(String keyName) {
        String value = System.getenv(keyName);

        if (value == null || value.trim().length() == 0) {
            throw new NullPointerException("Invalid Environment Variable Key Name: " + keyName);
        }

        return value;
    }
}
