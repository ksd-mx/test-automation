package main.java.com.framework.configuration.model.serialization;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestCase;
import main.java.com.framework.test.model.TestGroup;
import main.java.com.framework.test.model.TestPlan;
import main.java.com.framework.test.model.TestStep;

import java.io.IOException;

public class TestPlanSerializer {
    private final ObjectSerializer objectSerializer;

    public TestPlanSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public TestPlan retrieve(String fileName, boolean create) {
        TestPlan result = TestPlan.createMock();
        try {
            result = (TestPlan) this.objectSerializer
                    .ReadSettingsFromFile(
                            TestPlan.class,
                            fileName);

            for (TestGroup tg : result.getTestGroupList()) {
                for (TestCase tc : tg.getTestCaseList()) {
                    for (TestStep ts : tc.getTestStepList()) {
                        ts.setTestCase(tc);
                    }
                }
            }

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
