package main.java.com.framework.test.model.serialization;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestRun;

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

    public TestRun retrieve(String fileName, boolean create) {
        TestRun result = TestRun.createMock();

        try {
            result = (TestRun) this.objectSerializer
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

    public void save(TestRun testRun, String filename) throws Throwable {
        this.objectSerializer
                .WriteSettingsToFile(
                        testRun,
                        filename);

        System.out.println(String.format("Results written to %s", filename));
    }
}
