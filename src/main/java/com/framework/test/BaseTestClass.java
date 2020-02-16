package main.java.com.framework.test;

import main.java.com.framework.test.description.StepStrategy;
import main.java.com.framework.test.model.TestStep;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class BaseTestClass {

    private final ExecutionContext executionContext;

    public abstract void beforeClass(ITestContext testContext);

    public abstract void afterClass(ITestContext testContext);

    public abstract void beforeTest(ITestContext testContext);

    public abstract void afterTest(ITestContext testContext);

    public abstract void beforeMethod(ITestContext testContext, Method method);

    public abstract void afterMethod(ITestContext testContext, ITestResult result);

    public BaseTestClass(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    protected ExecutionContext getExecutionContext() throws Throwable {
        return this.executionContext;
    }

    @BeforeClass
    public final void onBeforeClass(ITestContext testContext) {
        this.beforeClass(testContext);
    }

    @AfterClass
    public final void onAfterClass(ITestContext testContext) throws Throwable {
        this.afterClass(testContext);
    }

    @BeforeTest
    public final void onBeforeTest(ITestContext testContext) throws Throwable {
        this.beforeTest(testContext);
    }

    @AfterTest
    public final void onAfterTest(ITestContext testContext) throws Throwable {
        this.afterTest(testContext);
    }

    @BeforeMethod
    public final void onBeforeMethod(ITestContext testContext, Method method) throws Throwable {
        this.beforeMethod(testContext, method);
    }

    @AfterMethod
    public final void onAfterMethod(ITestContext testContext, ITestResult result) throws Throwable {
        this.afterMethod(testContext, result);
    }

    protected <T extends StepStrategy> T getStepStrategy(TestStep testStep, Class<T> returnType) throws Throwable {
        Constructor<T> constructor = returnType.getConstructor(ExecutionContext.class, TestStep.class);

        return constructor.newInstance(this.executionContext, testStep);
    }
}