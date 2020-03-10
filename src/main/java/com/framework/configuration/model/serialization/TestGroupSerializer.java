package main.java.com.framework.configuration.model.serialization;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestGroup;
import main.java.com.framework.test.model.TestPlan;

import java.io.IOException;

public class TestGroupSerializer {
    private final ObjectSerializer objectSerializer;

    public TestGroupSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public TestPlan retrieve(String fileName, boolean create) {
        TestPlan result = null;
        try {
            System.out.println(String.format("Reading execution settings from file %s", fileName));
            result = (TestPlan) this.objectSerializer
                    .ReadSettingsFromFile(
                            TestPlan.class,
                            fileName);
        } catch (IOException e) {
            System.out.println(String.format("Failed reading %s", fileName));
            e.printStackTrace();
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

    public void save(TestPlan testPlan, String filename) {
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
