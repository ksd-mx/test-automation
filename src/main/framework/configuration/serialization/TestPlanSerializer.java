package main.framework.configuration.serialization;

import main.framework.serialization.ObjectSerializer;
import main.framework.test.model.TestPlan;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPlanSerializer {
    private final ObjectSerializer objectSerializer;

    public TestPlanSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public TestPlan retrieve(String fileName, boolean create) {
        TestPlan result = null;
        try {
            result = (TestPlan) this.objectSerializer
                    .ReadSettingsFromFile(
                            TestPlan.class,
                            fileName);
        } catch (FileNotFoundException e) {
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
