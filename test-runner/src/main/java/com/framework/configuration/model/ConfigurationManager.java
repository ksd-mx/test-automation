package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.*;
import main.java.com.framework.test.model.TestCase;
import main.java.com.framework.test.model.TestRun;
import main.java.com.framework.test.model.TestStep;

import java.io.IOException;

public final class ConfigurationManager {

    private static final String APPLICATION_SETTINGS_FILEPATH = "applicationsettings.json";
    private static final String TESTRUN_SETTINGS_FILEPATH = "testrun.json";
    private static final ObjectSerializer.FileType SERIALIZATION_FILE_FORMAT = ObjectSerializer.FileType.JSON;

    private static ConfigurationManager current;

    private IObjectSerializer objectSerializer;
    private ApplicationSettings applicationSettings;
    private TestRun testRun;

    private ConfigurationManager() {
        this.objectSerializer = ObjectSerializer.createSerializer(SERIALIZATION_FILE_FORMAT);

        this.applicationSettings = this.readApplicationSettingsFile();

        this.testRun = this.readTestRunFile();

        System.out.println(String.format("The file 'testrun' contains %s tests set up.", this.testRun.getTestCaseList().size()));
    }

    public static ConfigurationManager getCurrent() {
        if (ConfigurationManager.current == null)
            ConfigurationManager.current = new ConfigurationManager();

        return ConfigurationManager.current;
    }

    public ApplicationSettings getApplicationSettings() {
        return this.applicationSettings;
    }

    public TestRun getTestRun() {
        return this.testRun;
    }

    private ApplicationSettings readApplicationSettingsFile() {
        ApplicationSettings result = new ApplicationSettings();

        try {
            result =
                    (ApplicationSettings)
                            this.objectSerializer
                                    .ReadSettingsFromFile(
                                            ApplicationSettings.class,
                                            APPLICATION_SETTINGS_FILEPATH);
        } catch (IOException e) {
            this.objectSerializer.WriteSettingsToFile(
                    result,
                    APPLICATION_SETTINGS_FILEPATH);
        }
        finally {
            return result;
        }
    }

    private TestRun readTestRunFile() {
        TestRun result = new TestRun();

        try {
            result = (TestRun) this.objectSerializer.ReadSettingsFromFile(TestRun.class, TESTRUN_SETTINGS_FILEPATH);
        } catch (IOException e) {
            TestCase tc1 = new TestCase(1, "Testando 1");
            TestCase tc2 = new TestCase(2, "Testando 2");
            TestCase tc3 = new TestCase(3, "Testando 3");

            tc1.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
            tc1.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
            tc1.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

            tc2.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
            tc2.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
            tc2.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

            tc3.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
            tc3.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
            tc3.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

            result.getTestCaseList().add(tc1);
            result.getTestCaseList().add(tc2);
            result.getTestCaseList().add(tc3);

            this.objectSerializer.WriteSettingsToFile(result, TESTRUN_SETTINGS_FILEPATH);
        }
        finally {
            return result;
        }
    }
}
