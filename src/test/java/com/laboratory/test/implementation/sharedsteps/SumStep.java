package test.java.com.laboratory.test.implementation.sharedsteps;

import main.java.com.framework.test.ExecutionContext;
import main.java.com.framework.test.description.StepStrategy;
import main.java.com.framework.test.model.TestStep;
import org.testng.Assert;
import test.java.com.laboratory.test.implementation.testcases.Laboratory.Laboratory;

public class SumStep extends StepStrategy {
    public SumStep(ExecutionContext executionContext, TestStep step) {
        super(executionContext, step);
    }

    @Override
    public void onExecute() {
        Assert.assertEquals(new Laboratory().sumTwoNumbers(1, 1), 2, "Something went wrong!");
    }
}
