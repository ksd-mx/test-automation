package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestPlan extends DefaultTestArtifact {

    private List<TestCase> testCaseList;

    public TestPlan() {
        this.testCaseList = new ArrayList<>();
    }

    public List<TestCase> getTestCaseList() { return this.testCaseList; }
    protected void setTestCaseList(List<TestCase> value) { this.testCaseList = value; }

    public static TestPlan createMock() {
        TestPlan result = new TestPlan();

        TestCase tc1 = new TestCase("1", "Testando 1");
        TestCase tc2 = new TestCase("2", "Testando 2");
        TestCase tc3 = new TestCase("3", "Testando 3");

        tc1.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
        tc1.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
        tc1.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

        tc2.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
        tc2.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
        tc2.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

        tc3.getTestStepList().add(new TestStep("000000a", "Testing New Framework 1", "Success 1"));
        tc3.getTestStepList().add(new TestStep("000000b", "Testing New Framework 2", "Success 2"));
        tc3.getTestStepList().add(new TestStep("000000c", "Testing New Framework 3", "Success 3"));

        result.getTestCaseList().add(tc1);
        result.getTestCaseList().add(tc2);
        result.getTestCaseList().add(tc3);

        return result;
    }
}