package test.java.com.laboratory.test;

import main.java.com.laboratory.Laboratory;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LaboratoryTest {

    private Laboratory laboratory;

    public LaboratoryTest() {
        this.laboratory = new Laboratory();
    }

    @Test(description = "Testing the test method description for success")
    public void shouldPassSummingTwoNumbers() {
        Assert.assertTrue(laboratory.sumTwoNumbers(1, 1) == 2, "Something went wrong!");
    }

    @Test(description = "Testing the test method description for failure")
    public void shouldFailSummingTwoNumbers() {
        Assert.assertFalse(laboratory.sumTwoNumbersFail(1, 1) == 2, "Something went wrong!");
    }

}
