package main.framework.test;

import main.framework.configuration.ConfigurationManager;
import main.framework.configuration.serialization.ExecutionSettingsSerializer;
import main.framework.configuration.serialization.WebElementMappingSerializer;
import main.framework.configuration.serialization.TestPlanSerializer;
import main.framework.services.LocalServiceCollection;
import main.framework.serialization.ObjectSerializer;
import main.framework.test.selenium.WebDriverManager;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ExecutionContext {
    // Singleton instance backing property
    private static ExecutionContext current;

    private final LocalServiceCollection localServiceCollection;

    private final String contextId;

    private ExecutionContext(String contextId, LocalServiceCollection localServiceCollection) {
        this.localServiceCollection = localServiceCollection;

        this.contextId = contextId;

        Runtime.getRuntime().addShutdownHook(new Thread(this::onExitRuntime));
    }

    public final LocalServiceCollection getLocalServiceCollection() {
        return this.localServiceCollection;
    }

    public synchronized static ExecutionContext getCurrent() {
        if (ExecutionContext.current == null) {
            String contextId = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

            ConfigurationManager configurationManager =
                    new ConfigurationManager(contextId,
                            new ExecutionSettingsSerializer(ObjectSerializer.DataFormat.YAML),
                            new WebElementMappingSerializer(ObjectSerializer.DataFormat.YAML),
                            new TestPlanSerializer(ObjectSerializer.DataFormat.JSON));

            configurationManager.initialize();

            WebDriverManager webDriverManager = new WebDriverManager(configurationManager);

            LocalServiceCollection localServiceCollection = new LocalServiceCollection();

            localServiceCollection.put(ConfigurationManager.SERVICE_KEY, configurationManager);
            localServiceCollection.put(WebDriverManager.SERVICE_KEY, webDriverManager);

            ExecutionContext.current = new ExecutionContext(contextId, localServiceCollection);
        }

        return ExecutionContext.current;
    }

    private void onExitRuntime() {
        try {
            this.localServiceCollection.clear();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
