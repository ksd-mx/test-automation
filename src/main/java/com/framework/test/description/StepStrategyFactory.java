package main.java.com.framework.test.description;

import main.java.com.framework.test.RunContext;
import main.java.com.framework.test.model.TestStep;

import java.lang.reflect.Constructor;

public class StepStrategyFactory {
    private String testCaseId;
    private TestStep step;

    public StepStrategyFactory(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public <T extends StepStrategy> T getStepStrategy(String actionPath, Class<T> returnType) throws Throwable {
        this.step = RunContext.getCurrent().getTestStepById(this.testCaseId, actionPath);

        Constructor<T> constructor = returnType.getConstructor(TestStep.class);

        return constructor.newInstance(this.step);
    }
}