package test.java.com.laboratory.test;

import main.java.com.framework.test.Step;
import test.java.com.laboratory.test.implementation.DefaultTestImpl;
import test.java.com.laboratory.test.implementation.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends DefaultTestImpl {

    @Test()
    public void shouldPassSummingTwoNumbers() {
        this.someTestStep01();
        this.someTestStep02();
        Assert.assertTrue(new Laboratory().sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        this.someTestStep03();
        this.someTestStep04();
        Assert.assertFalse(new Laboratory().sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

    @Step(testCaseId = "1", actionPath = "000000a", screenshotBefore = true, screenshotAfter = true)
    public void someTestStep01() {
        System.out.println("Step 1");
    }

    @Step(testCaseId = "1", actionPath = "000000b", screenshotBefore = true, screenshotAfter = true)
    public void someTestStep02() {
        System.out.println("Step 2");
    }

    @Step(testCaseId = "2", actionPath = "000000a", screenshotBefore = true, screenshotAfter = true)
    public void someTestStep03() {
        System.out.println("Step 1");
    }

    @Step(testCaseId = "2", actionPath = "000000b", screenshotBefore = true, screenshotAfter = true)
    public void someTestStep04() {
        System.out.println("Step 2");
    }

}