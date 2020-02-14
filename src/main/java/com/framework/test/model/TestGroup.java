package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestGroup extends DefaultTestArtifact {

    private List<TestCase> testCaseList;

    public TestGroup() {
        this.testCaseList = new ArrayList<>();
    }

    public List<TestCase> getTestCaseList() { return this.testCaseList; }
    protected void setTestCaseList(List<TestCase> value) { this.testCaseList = value; }
}
