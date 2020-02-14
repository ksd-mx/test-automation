package main.java.com.framework.test.model.serialization;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestPlan;

import java.io.IOException;

public class TestRunSerializer {
    private final ObjectSerializer objectSerializer;

    public TestRunSerializer() {
        this(ConfigurationManager
                        .getCurrent()
                        .getApplicationSettings()
                        .getDefaultSerializationDataType());
    }

    public TestRunSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public TestPlan retrieve(String fileName, boolean create) {
        TestPlan result = TestPlan.createMock();

        try {
            result = (TestPlan) this.objectSerializer
                    .ReadSettingsFromFile(
                            ApplicationSettings.class,
                            fileName);
        } catch (IOException e) {
            if (create) {
                this.objectSerializer.WriteSettingsToFile(
                        result,
                        fileName);
            }
        }
        finally {
            return result;
        }
    }

    public void save(TestPlan testPlan, String filename) throws Throwable {
        this.objectSerializer
                .WriteSettingsToFile(
                        testPlan,
                        filename);
    }
}
