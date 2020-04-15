package main.framework.test.model;

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

    public List<TestStep> getTestStepList() {
        return this.testStepList;
    }

    public TestStep getTestStep(String testStepId) throws NullPointerException {
        for (TestStep testStep : this.getTestStepList()) {
            if (testStep.getExternalId().equals(testStepId)) {
                testStep.setTestCase(this);

                return testStep;
            }
        }

        throw new NullPointerException(String.format("TEST STEP NOT FOUND: %s:%s", this.getExternalId(), testStepId));
    }
}