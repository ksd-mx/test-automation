package main.framework.test.selenium;

import main.framework.configuration.ConfigurationManager;
import main.framework.data.IDataReader;
import main.framework.services.LocalServiceCollection;
import main.framework.test.description.StepStrategy;
import main.framework.test.model.Result;
import main.framework.test.model.TestCase;
import main.framework.test.model.TestStep;

import org.junit.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class BaseTestCase implements ITest {
    private final LocalServiceCollection localServiceCollection;
    private final TestCase testCase;

    protected BaseTestCase(LocalServiceCollection localServiceCollection, String testCaseId) {
        this.localServiceCollection = localServiceCollection;

        this.testCase = this.getConfigurationManager()
                .getTestPlanSettings()
                .getTestCase(testCaseId);
    }

    protected LocalServiceCollection getLocalServiceCollection() {
        return this.localServiceCollection;
    }

    protected ConfigurationManager getConfigurationManager() {
        return this.getLocalServiceCollection().get(ConfigurationManager.SERVICE_KEY);
    }

    public void runTest(IDataReader dataReader) {
        if (this.testCase == null) return;

        try {
            this.onRunTest(dataReader);

            this.testCase.setResult(Result.PASSED);
        } catch(Exception ex) {
            this.testCase.setResult(Result.FAILED);

            Assert.fail(ex.getMessage());
        }
    }

    protected abstract void onRunTest(IDataReader dataReader) throws Exception;

    public TestCase getTestCase() {
        return this.testCase;
    }

    @Override
    public String getTestName() {
        return this.testCase.getName();
    }

    @BeforeClass
    public void beforeClass(ITestContext testContext) {
        System.out.println(String.format("STARTING CLASS %s", this.getClass().toString()));
    }

    @AfterClass
    public void afterClass(ITestContext testContext) {
        System.out.println(String.format("EXITING CLASS: %s", this.getClass().toString()));
    }

    @BeforeTest
    public void beforeTest(ITestContext testContext) {
        System.out.println(String.format("STARTING TEST: %s", testContext.getCurrentXmlTest().getName()));
    }

    @AfterTest
    public void afterTest(ITestContext testContext) {
        System.out.println(String.format("FINISHING TEST: %s", testContext.getCurrentXmlTest().getName()));
    }

    @BeforeMethod
    public void beforeMethod(ITestContext testContext, Method method) {
        System.out.println(String.format("STARTING TEST METHOD: %s", method.getName()));
    }

    @AfterMethod
    public void afterMethod(ITestContext testContext, ITestResult result) {
        System.out.println(String.format("FINISHING TEST: %s WITH RESULT %s", result.getTestName(), result.getName()));

        Result testResult;

        switch (result.getStatus()) {
            case 1:
                testResult = Result.PASSED;
                break;
            case 2:
                testResult = Result.FAILED;
                break;
            case 0:
                testResult = Result.NOTEXECUTED;
                break;
            default:
                testResult = Result.INCONCLUSIVE;
        }

        this.getTestCase().setResult(testResult);
    }

    protected <T extends StepStrategy> T getStep(String testStepId, Class<T> returnType) {
        T result = null;
        try {
            WebDriverManager webDriverManager = this.localServiceCollection.get(WebDriverManager.SERVICE_KEY);
            Constructor<T> constructor = returnType.getConstructor(WebDriverManager.class, TestStep.class);

            result = constructor.newInstance(webDriverManager, testStepId);
        } catch (NoSuchMethodException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}