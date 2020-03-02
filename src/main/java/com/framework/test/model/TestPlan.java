package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestPlan extends TestArtifact {

    private List<TestCase> testCaseList;

    public TestPlan() {
        this.testCaseList = new ArrayList<>();
    }

    public TestPlan(String externalId, String name) {
        super(externalId, name, 1);

        this.testCaseList = new ArrayList<>();
    }

    public List<TestCase> getTestCaseList() {
        return this.testCaseList;
    }

    public TestCase getTestCase(String testCaseId) {
        for (TestCase tc : this.testCaseList) {
            if (tc.getExternalId().equals(testCaseId))
                return tc;
        }
        return null;
    }

    public TestStep getTestStep(String testCaseId, String stepId) {
        TestCase tc = this.getTestCase(testCaseId);

        for (TestStep step : tc.getTestStepList()) {
            if (step.getExternalId().equals(stepId))
                return step;
        }
        return null;
    }
}
