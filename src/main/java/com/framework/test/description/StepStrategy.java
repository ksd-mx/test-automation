package main.java.com.framework.test.description;

import main.java.com.framework.test.ExecutionContext;
import main.java.com.framework.test.model.Result;
import main.java.com.framework.test.model.TestStep;

import java.time.LocalDateTime;

public abstract class StepStrategy implements IStepStrategy {
    private ExecutionContext executionContext;
    private TestStep step;

    public StepStrategy(ExecutionContext executionContext, TestStep step) {
        this.executionContext = executionContext;
        this.step = step;
    }

    public final void execute() throws Throwable{
        this.executionContext.takeScreenshot(this.step, "BEFORE");
        this.step.setStart(LocalDateTime.now().toString());
        this.onExecute();
        this.step.setFinish(LocalDateTime.now().toString());
        this.executionContext.takeScreenshot(this.step, "AFTER");
    }

    public abstract void onExecute() throws Throwable;

    protected void setOutputValue(String key, String value) {
        this.step.getTestCase().getOutputValueList().put(key, value);
    }

    protected void setFieldValue(String key, String value) {
        this.executionContext.setTestFieldValue(this.step, key, value);
    }

    protected void setStepResult(Result stepResult) {
        this.step.setResult(stepResult);
    }
}