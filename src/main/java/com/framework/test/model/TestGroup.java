package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestGroup extends TestArtifact {

    private List<TestGroup> testGroupList;
    private List<TestCase> testCaseList;

    public TestGroup() {
        this.testGroupList = new ArrayList<>();
        this.testCaseList = new ArrayList<>();
    }

    public TestGroup(String externalId, String name) {
        super(externalId, name, 1);

        this.testGroupList = new ArrayList<>();
        this.testCaseList = new ArrayList<>();
    }

    public List<TestGroup> getTestGroupList() {
        return this.testGroupList;
    }

    public List<TestCase> getTestCaseList() {
        return this.testCaseList;
    }

    public TestStep getTestStep(String testCaseId, String stepId) {
        for (TestGroup group : this.testGroupList) {
            return this.searchTestStep(group, testCaseId, stepId);
        }
        return null;
    }

    private TestStep searchTestStep(TestGroup group, String testCaseId, String stepId) {
        if (group.getTestCaseList() != null) {
            for (TestCase testCase : group.getTestCaseList()) {
                for (TestStep testStep : testCase.getTestStepList()) {
                    if (testCase.getExternalId().equals(testCaseId) && testStep.getExternalId().equals(stepId)) {
                        testStep.setTestCase(testCase);

                        return testStep;
                    }
                }
            }
        }

        if (group.getTestGroupList() != null) {
            for (TestGroup childGroup : group.getTestGroupList()) {
                return this.searchTestStep(childGroup, testCaseId, stepId);
            }
        }

        return null;
    }
}
