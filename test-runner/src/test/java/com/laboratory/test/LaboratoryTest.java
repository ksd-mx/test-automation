package test.java.com.laboratory.test;

import main.java.com.framework.test.automation.*;
import test.java.com.laboratory.test.implementation.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends AutomatedTest {

    private final Laboratory laboratory;

    public LaboratoryTest() {
        this.laboratory = new Laboratory();
    }

    @Override
    public String getTestName() { return "Laboratory"; }

    @Test()
    public void shouldPassSummingTwoNumbers() {
        Assert.assertTrue(laboratory.sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        Assert.assertFalse(laboratory.sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

}