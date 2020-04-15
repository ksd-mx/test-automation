package main.framework.configuration;

import main.framework.configuration.model.ExecutionSettings;
import main.framework.configuration.model.selenium.WebElementMappingCollection;
import main.framework.configuration.serialization.ExecutionSettingsSerializer;
import main.framework.configuration.serialization.WebElementMappingSerializer;
import main.framework.configuration.serialization.TestPlanSerializer;
import main.framework.services.DefaultLocalService;
import main.framework.test.model.TestPlan;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ConfigurationManager extends DefaultLocalService {
    public static final String SERVICE_KEY = "configuration.manager";

    private final ExecutionSettingsSerializer executionSettingsSerializer;
    private final WebElementMappingSerializer webElementMappingSerializer;
    private final TestPlanSerializer testPlanSettingsSerializer;

    private Path resultFilePath;

    private ExecutionSettings executionSettings;
    private WebElementMappingCollection webElementMappingCollection;
    private TestPlan testPlanSettings;

    public ConfigurationManager(String contextId,
                                ExecutionSettingsSerializer executionSettingsSerializer,
                                WebElementMappingSerializer webElementMappingSerializer,
                                TestPlanSerializer testPlanSettingsSerializer) {
        super(contextId);
        this.executionSettingsSerializer = executionSettingsSerializer;
        this.webElementMappingSerializer = webElementMappingSerializer;
        this.testPlanSettingsSerializer = testPlanSettingsSerializer;
    }

    public ExecutionSettings getExecutionSettings() {
        return this.executionSettings;
    }

    public WebElementMappingCollection getWebElementMappingCollection() {
        return this.webElementMappingCollection;
    }

    public TestPlan getTestPlanSettings() {
        return this.testPlanSettings;
    }

    @Override
    public void initialize() {
        this.resultFilePath = Paths.get(
                ExecutionSettings.RESULT_FILEPATH,
                ExecutionSettings.RESULT_FILENAME);

        this.executionSettings = this.executionSettingsSerializer.retrieve(ExecutionSettings.EXECUTION_SETTINGS_FILEPATH, true);
        this.webElementMappingCollection = this.webElementMappingSerializer.retrieve(ExecutionSettings.FIELD_SETTINGS_FILEPATH, true);
        this.testPlanSettings = this.testPlanSettingsSerializer.retrieve(ExecutionSettings.TEST_PLAN_FILENAME, true);
    }

    @Override
    public void close() {
        try {
            this.testPlanSettingsSerializer.save(this.testPlanSettings, this.resultFilePath.toString());
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
