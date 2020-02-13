package main.java.com.framework.configuration;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.configuration.model.serialization.ApplicationSettingsSerializer;
import main.java.com.framework.serialization.*;
import main.java.com.framework.test.model.TestCase;
import main.java.com.framework.test.model.TestRun;
import main.java.com.framework.test.model.TestStep;
import main.java.com.framework.test.model.serialization.TestRunSerializer;

import java.io.IOException;

public final class ConfigurationManager {

    public static final String APPLICATION_SETTINGS_FILEPATH = "applicationsettings.yml";
    public static final String TESTRUN_SETTINGS_FILEPATH = "testrun.json";

    private static ConfigurationManager current;

    private ApplicationSettings applicationSettings;
    private TestRun testRunSettings;

    private ConfigurationManager() {
        ApplicationSettingsSerializer applicationSettingsSerializer = new ApplicationSettingsSerializer(ObjectSerializer.DataFormat.YAML);
        TestRunSerializer testRunSerializer = new TestRunSerializer(ObjectSerializer.DataFormat.JSON);

        this.applicationSettings = applicationSettingsSerializer.retrieve(APPLICATION_SETTINGS_FILEPATH, true);
        this.testRunSettings = testRunSerializer.retrieve(TESTRUN_SETTINGS_FILEPATH, true);
    }

    public static synchronized ConfigurationManager getCurrent() {
        if (ConfigurationManager.current == null)
            ConfigurationManager.current = new ConfigurationManager();

        return ConfigurationManager.current;
    }

    public ApplicationSettings getApplicationSettings() {
        return this.applicationSettings;
    }

    public TestRun getTestRunSettings() {
        return this.testRunSettings;
    }

}
