package main.java.com.framework.test;

import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.configuration.model.serialization.TestPlanSerializer;
import main.java.com.framework.test.model.TestField;
import main.java.com.framework.test.model.TestPlan;
import main.java.com.framework.test.model.TestStep;
import main.java.com.framework.test.selenium.WebDriverManager;
import org.openqa.selenium.NotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecutionContext {
    private static final String TESTRUN_SETTINGS_FILEPATH = "execution-context.json";
    private static final String TESTRUN_RESULT_FILENAME = "execution-result.json";

    private final Path resultFilePath;

    private final TestPlanSerializer testPlanSettingsSerializer;
    private final TestPlan testPlanSettings;

    private final ConfigurationManager configurationManager;
    private final WebDriverManager webDriverManager;

    public ExecutionContext(
            ConfigurationManager configurationManager,
            WebDriverManager webDriverManager,
            TestPlanSerializer testPlanSettingsSerializer) {

        this.resultFilePath = Paths.get(ConfigurationManager.DEFAULT_RESULT_FOLDER_PATH, TESTRUN_RESULT_FILENAME);
        this.configurationManager = configurationManager;
        this.webDriverManager = webDriverManager;
        this.testPlanSettingsSerializer = testPlanSettingsSerializer;
        this.testPlanSettings = this.testPlanSettingsSerializer.retrieve(TESTRUN_SETTINGS_FILEPATH, true);

        Runtime.getRuntime().addShutdownHook(new Thread(this::onExitRuntime));

        this.webDriverManager.startWebDriver();
    }

    public void getScreenShot(TestStep testStep, String filesufix) throws Throwable {
        Path filePath =
                Paths.get(
                        this.configurationManager.getApplicationSettings().getResultPath(),
                        testStep.getTestCase().getExternalId(),
                        testStep.getActionPath(),
                        String.format("SCREENSHOT_%s_%s.png", testStep.getExternalId(), filesufix));

        this.webDriverManager.getScreenShot(filePath.toString());

        testStep.getScreenshotList().add(filePath.toString());
    }

    public TestField getTestFieldFromSettings(String fieldKey) {
        for (TestField field : this.configurationManager.getFieldSettings()) {
            if (field.getKey().equals(fieldKey)) return field;
        }
        throw new NotFoundException(String.format("There are no settings for %s", fieldKey));
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
}
