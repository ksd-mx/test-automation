package test.java.com.laboratory.test;

import main.java.com.framework.test.ExecutionContext;
import main.java.com.framework.test.description.IStepStrategy;
import test.java.com.laboratory.test.implementation.DefaultTestImpl;
import test.java.com.laboratory.test.implementation.sharedsteps.SumStep;
import test.java.com.laboratory.test.implementation.testcases.Laboratory.Laboratory;
import org.testng.annotations.*;
import org.testng.Assert;

public class LaboratoryTest extends DefaultTestImpl {
    private final IStepStrategy sumStep;

    public LaboratoryTest(ExecutionContext executionContext) throws Throwable {
        super(executionContext);

        this.sumStep = this.getStepStrategy(null, SumStep.class);
    }

    @Test()
    public void shouldPassSummingTwoNumbers() {
        this.sumStep.execute();
    }

    @Test()
    public void shouldFailSummingTwoNumbers() {
        this.sumStep.execute();
        Assert.assertNotEquals(new Laboratory().sumTwoNumbersFail(1, 1), 2, "Something went wrong!");
    }

}