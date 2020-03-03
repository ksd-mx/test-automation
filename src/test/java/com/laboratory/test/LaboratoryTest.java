package test.java.com.laboratory.test;

import main.java.com.framework.test.ExecutionContext;
import main.java.com.framework.test.description.IStepStrategy;
import main.java.com.framework.test.model.TestStep;
import test.java.com.laboratory.test.implementation.DefaultTestImpl;
import test.java.com.laboratory.test.implementation.sharedsteps.SumStep;
import test.java.com.laboratory.test.implementation.testcases.Laboratory.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends DefaultTestImpl {
    private final TestStep sumStep1;
    private final TestStep sumStep2;

    private final IStepStrategy sumStepImpl1;
    private final IStepStrategy sumStepImpl2;

    public LaboratoryTest() throws Throwable {
        super(ExecutionContext.getCurrent().getTestPlan().getTestCase("4605"));

        this.sumStep1 = this.getTestCase().getTestStep("0");
        this.sumStep2 = this.getTestCase().getTestStep("1");

        this.sumStepImpl1 = this.getStepStrategy(this.sumStep1, SumStep.class);
        this.sumStepImpl2 = this.getStepStrategy(this.sumStep2, SumStep.class);
    }

    @Test()
    public void shouldPassSummingTwoNumbers() {
        this.sumStepImpl1.execute();
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        this.sumStepImpl2.execute();
        Assert.assertNotEquals(new Laboratory().sumTwoNumbersFail(1, 1), 2, "Something went wrong!");
    }
}