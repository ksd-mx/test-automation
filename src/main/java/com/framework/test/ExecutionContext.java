package main.java.com.framework.test;

import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.configuration.model.serialization.ApplicationSettingsSerializer;
import main.java.com.framework.configuration.model.serialization.FieldSettingsSerializer;
import main.java.com.framework.configuration.model.serialization.TestGroupSerializer;
import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestField;
import main.java.com.framework.test.model.TestPlan;
import main.java.com.framework.test.model.TestStep;
import main.java.com.framework.test.selenium.DriverFactory;
import main.java.com.framework.test.selenium.WebDriverManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecutionContext {
    // Singleton instance backing property
    private static ExecutionContext current;

    private static final String TESTRUN_SETTINGS_FILEPATH = "execution-context.json";
    private static final String TESTRUN_RESULT_FILENAME = "execution-result.json";

    private final Path resultFilePath;

    private final TestGroupSerializer testPlanSettingsSerializer;
    private final TestPlan testPlanSettings;

    private final ConfigurationManager configurationManager;
    private final WebDriverManager webDriverManager;

    //TODO: Create an ExcelManager to work as a plugin much like WebDriverManager

    private ExecutionContext(
            ConfigurationManager configurationManager,
            WebDriverManager webDriverManager,
            TestGroupSerializer testPlanSettingsSerializer) {

        this.configurationManager = configurationManager;
        this.resultFilePath = Paths.get(
                this.configurationManager.getApplicationSettings().getResultPath(),
                ExecutionContext.TESTRUN_RESULT_FILENAME);
        this.webDriverManager = webDriverManager;
        this.testPlanSettingsSerializer = testPlanSettingsSerializer;
        this.testPlanSettings = this.testPlanSettingsSerializer.retrieve(TESTRUN_SETTINGS_FILEPATH, false);

        Runtime.getRuntime().addShutdownHook(new Thread(this::onExitRuntime));

        this.webDriverManager.startWebDriver();
    }

    public TestPlan getTestPlan() {
        return this.testPlanSettings;
    }

    public synchronized static ExecutionContext getCurrent() {
        if (ExecutionContext.current == null)
            ExecutionContext.current = new ExecutionContext(
                    new ConfigurationManager(
                            new ApplicationSettingsSerializer(ObjectSerializer.DataFormat.YAML),
                            new FieldSettingsSerializer(ObjectSerializer.DataFormat.YAML)),
                    new WebDriverManager(DriverFactory.DriverType.CHROME),
                    new TestGroupSerializer(ObjectSerializer.DataFormat.JSON));

        return ExecutionContext.current;
    }

    public void setTestFieldValue(TestStep testStep, String key, String value) {
        if (this.configurationManager.getFieldSettings() != null) {
            TestField testField = this.configurationManager.getFieldSettings().get(key);
            testField.setValue(value);

            if (!testStep.getTestFieldList().contains(testStep))
                testStep.getTestFieldList().add(testField);
        }
    }

    public void takeScreenshot(TestStep testStep, String filesufix) throws Throwable {
        Path filePath =
                Paths.get(
                        this.configurationManager.getApplicationSettings().getResultPath(),
                        testStep.getTestCase().getExternalId(),
                        testStep.getExternalId(),
                        String.format("SCREENSHOT_%s_%s_%s.png",
                                testStep.getTestCase().getExternalId(),
                                testStep.getExternalId(),
                                filesufix));

        System.out.println(String.format("Saving screenshot to %s", filePath.toString()));

        this.webDriverManager.getScreenShot(filePath.toString());

        testStep.getScreenshotList().add(filePath.toString());
    }

    private void exportExecutionResults() {
        try {
            this.testPlanSettingsSerializer
                    .save(this.testPlanSettings, this.resultFilePath.toString());
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private void onExitRuntime() {
        try {
            this.exportExecutionResults();

            this.webDriverManager.stopWebDriver();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
}
