package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.YamlSerializer;

public final class ConfigurationManager {
    private static ConfigurationManager current;

    private ApplicationSettings applicationSettings;

    private ConfigurationManager() throws Throwable {
        this.applicationSettings =
                (ApplicationSettings)
                        YamlSerializer
                                .ReadSettingsFromFile(ApplicationSettings.class, "applicationsettings.yaml");
    }

    public static ConfigurationManager getCurrent() throws Throwable {
        if (ConfigurationManager.current == null)
            ConfigurationManager.current = new ConfigurationManager();

        return ConfigurationManager.current;
    }
}
