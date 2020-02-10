package test.java.com.laboratory.test;

import main.java.com.framework.test.automation.selenium.SeleniumAutomatedTest;
import test.java.com.laboratory.test.implementation.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends SeleniumAutomatedTest {

    @Test()
    public void shouldPassSummingTwoNumbers() {
        Assert.assertTrue(new Laboratory().sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        Assert.assertFalse(new Laboratory().sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

}