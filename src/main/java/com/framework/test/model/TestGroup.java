package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestGroup extends TestArtifact {

    private List<TestCase> testCaseList;

    public TestGroup() {
        this.testCaseList = new ArrayList<>();
    }

    public TestGroup(String externalId, String name) {
        super(externalId, name, 1);

        this.testCaseList = new ArrayList<>();
    }

    public List<TestCase> getTestCaseList() {
        return this.testCaseList;
    }

    public TestCase getTestCaseById(String id) {
        for (TestCase tc : this.getTestCaseList()) {
            if (tc.getExternalId().equals(id))
                return tc;
        }
        return null;
    }
}
