package test.java.com.laboratory.test;

import main.java.com.framework.AutomatedTest;
import main.java.com.laboratory.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends AutomatedTest {

    private final Laboratory laboratory;

    public LaboratoryTest() {
        this.laboratory = new Laboratory();
    }

    public void shouldPassSummingTwoNumbers() {
        Assert.assertTrue(laboratory.sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    public void shouldFailSummingTwoNumbers() {
        Assert.assertFalse(laboratory.sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

}
