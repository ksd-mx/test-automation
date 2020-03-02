package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

public class TestCase extends TestArtifact {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TestGroup testGroup;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, String> outputValueList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<TestStep> testStepList;

    public TestCase() {
        this.outputValueList = new HashMap<>();
        this.testStepList = new ArrayList<>();
    }

    public TestCase(String id, String name, int priority) {
        super(id, name, priority);

        this.outputValueList = new HashMap<>();
        this.testStepList = new ArrayList<>();
    }

    public TestGroup getTestGroup() {
        return this.testGroup;
    }

    public Map<String, String> getOutputValueList() {
        return this.outputValueList;
    }

    public List<TestStep> getTestStepList() {
        return this.testStepList;
    }

    public TestStep getTestStep(String testStepId) {
        for (TestStep testStep : this.getTestStepList()) {
            if (testStep.getExternalId().equals(testStepId)) {
                testStep.setTestCase(this);

                return testStep;
            }
        }
        return null;
    }
}