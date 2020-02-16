package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

public class TestCase extends TestArtifact {

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

    public Map<String, String> getOutputValueList() {
        return this.outputValueList;
    }

    public List<TestStep> getTestStepList() {
        return this.testStepList;
    }

    public TestStep getTestStep(String actionPath) {
        for (TestStep ts : this.getTestStepList()) {
            if (ts.getActionPath().equals(actionPath)) return ts;
        }
        return null;
    }
}