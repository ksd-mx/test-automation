package main.java.com.framework.configuration;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.configuration.model.serialization.ApplicationSettingsSerializer;
import main.java.com.framework.serialization.*;
import main.java.com.framework.test.model.TestPlan;
import main.java.com.framework.test.model.serialization.TestRunSerializer;

public final class ConfigurationManager {

    public static final String APPLICATION_SETTINGS_FILEPATH = "applicationsettings.yml";
    public static final String TESTRUN_SETTINGS_FILEPATH = "testrun.json";

    private static ConfigurationManager current;

    private ApplicationSettings applicationSettings;
    private TestPlan testPlanSettings;

    private ConfigurationManager() {
        ApplicationSettingsSerializer applicationSettingsSerializer = new ApplicationSettingsSerializer(ObjectSerializer.DataFormat.YAML);
        TestRunSerializer testRunSerializer = new TestRunSerializer(ObjectSerializer.DataFormat.JSON);

        this.applicationSettings = applicationSettingsSerializer.retrieve(APPLICATION_SETTINGS_FILEPATH, true);
        this.testPlanSettings = testRunSerializer.retrieve(TESTRUN_SETTINGS_FILEPATH, true);
    }

    public static synchronized ConfigurationManager getCurrent() {
        if (ConfigurationManager.current == null)
            ConfigurationManager.current = new ConfigurationManager();

        return ConfigurationManager.current;
    }

    public ApplicationSettings getApplicationSettings() {
        return this.applicationSettings;
    }

    public TestPlan getTestPlanSettings() {
        return this.testPlanSettings;
    }

}
