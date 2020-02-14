package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestCase extends DefaultTestArtifact {

    private List<TestStep> testStepList;

    public TestCase() {
        this.testStepList = new ArrayList<>();
    }

    public TestCase(String id, String name) {
        this();

        super.setExternalId(id);
        super.setName(name);
    }

    public List<TestStep> getTestStepList() { return this.testStepList; }
    protected void setTestStepList(List<TestStep> value) { this.testStepList = value; }
}