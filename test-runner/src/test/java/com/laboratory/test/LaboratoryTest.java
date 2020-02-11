package test.java.com.laboratory.test;

import main.java.com.framework.test.Step;
import main.java.com.framework.test.automation.selenium.SeleniumAutomatedTest;
import test.java.com.laboratory.test.implementation.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends SeleniumAutomatedTest {

    public LaboratoryTest() throws Throwable {
    }

    @Test()
    public void shouldPassSummingTwoNumbers() {
        this.someTestStepFirst();
        this.someTestStepSecond();
        Assert.assertTrue(new Laboratory().sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        this.someTestStepFirst();
        this.someTestStepSecond();
        Assert.assertFalse(new Laboratory().sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

    @Step(testCaseId = 1, actionPath = "000000a")
    public void someTestStepFirst() {
        System.out.println("Step 1");
    }

    @Step(testCaseId = 2, actionPath = "000000b")
    public void someTestStepSecond() {
        System.out.println("Step 2");
    }
}