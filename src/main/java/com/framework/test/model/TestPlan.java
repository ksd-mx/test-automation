package main.java.com.framework.test.model;

import java.util.ArrayList;
import java.util.List;

public class TestPlan extends TestArtifact {

    private final List<TestGroup> testGroupList;

    public TestPlan() {
        this.testGroupList = new ArrayList<>();
    }

    public TestPlan(String externalId, String name) {
        super(externalId, name, 1);

        this.testGroupList = new ArrayList<>();
    }

    public List<TestGroup> getTestGroupList() {
        return this.testGroupList;
    }

    public static TestPlan createMock() {
        TestPlan result = new TestPlan();

        TestGroup tg1 = new TestGroup("1", "Group 1");

        TestCase tc1 = new TestCase("1", "Testando 1", 1);
        TestCase tc2 = new TestCase("2", "Testando 2", 2);
        TestCase tc3 = new TestCase("3", "Testando 3", 3);

        tc1.getTestStepList().add(new TestStep(tc1,"000000a", "Testing New Framework 1", "Success 1"));
        tc1.getTestStepList().add(new TestStep(tc1,"000000b", "Testing New Framework 2", "Success 2"));
        tc1.getTestStepList().add(new TestStep(tc1,"000000c", "Testing New Framework 3", "Success 3"));

        tc2.getTestStepList().add(new TestStep(tc2,"000000a", "Testing New Framework 1", "Success 1"));
        tc2.getTestStepList().add(new TestStep(tc2,"000000b", "Testing New Framework 2", "Success 2"));
        tc2.getTestStepList().add(new TestStep(tc2,"000000c", "Testing New Framework 3", "Success 3"));

        tc3.getTestStepList().add(new TestStep(tc3,"000000a", "Testing New Framework 1", "Success 1"));
        tc3.getTestStepList().add(new TestStep(tc3,"000000b", "Testing New Framework 2", "Success 2"));
        tc3.getTestStepList().add(new TestStep(tc3,"000000c", "Testing New Framework 3", "Success 3"));

        tg1.getTestCaseList().add(tc1);
        tg1.getTestCaseList().add(tc2);
        tg1.getTestCaseList().add(tc3);

        result.testGroupList.add(tg1);

        return result;
    }
}