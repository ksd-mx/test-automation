package main.java.com.framework.test;

import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseTestClass implements ITest {

    public abstract String getTestName();

    public abstract void beforeClass(ITestContext testContext);

    public abstract void afterClass(ITestContext testContext);

    public abstract void beforeTest(ITestContext testContext);

    public abstract void afterTest(ITestContext testContext);

    public abstract void beforeMethod(ITestContext testContext, Method method);

    public abstract void afterMethod(ITestContext testContext, ITestResult result);

    @BeforeClass
    public final void onBeforeClass(ITestContext testContext) {
        this.beforeClass(testContext);
    }

    @AfterClass
    public final void onAfterClass(ITestContext testContext) {
        this.afterClass(testContext);
    }

    @BeforeTest
    public final void onBeforeTest(ITestContext testContext) {
        this.beforeTest(testContext);
    }

    @AfterTest
    public final void onAfterTest(ITestContext testContext) {
        this.afterTest(testContext);
    }

    @BeforeMethod
    public final void onBeforeMethod(ITestContext testContext, Method method) {
        this.beforeMethod(testContext, method);
    }

    @AfterMethod
    public final void onAfterMethod(ITestContext testContext, ITestResult result) {
        this.afterMethod(testContext, result);
    }

}