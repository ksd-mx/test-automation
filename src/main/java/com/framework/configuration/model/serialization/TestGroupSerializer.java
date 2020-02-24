package main.java.com.framework.configuration.model.serialization;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestGroup;

import java.io.IOException;

public class TestGroupSerializer {
    private final ObjectSerializer objectSerializer;

    public TestGroupSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public TestGroup retrieve(String fileName, boolean create) {
        TestGroup result = null;
        try {
            System.out.println(String.format("Reading execution settings from file %s", fileName));
            result = (TestGroup) this.objectSerializer
                    .ReadSettingsFromFile(
                            TestGroup.class,
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

    public void save(TestGroup testPlan, String filename) {
        try {
            System.out.println(String.format("Saving execution results to file %s", filename));
            this.objectSerializer
                    .WriteSettingsToFile(
                            testPlan,
                            filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
