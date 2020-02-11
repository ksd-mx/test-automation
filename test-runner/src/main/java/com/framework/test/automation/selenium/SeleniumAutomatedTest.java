package main.java.com.framework.test.automation.selenium;

import main.java.com.framework.test.BaseTestClass;
import main.java.com.framework.test.automation.AutomatedTest;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public abstract class SeleniumAutomatedTest extends AutomatedTest {
    public SeleniumAutomatedTest() {
    }

    @Override
    public void beforeClass(ITestContext testContext) {
        System.out.println(":: Before Class " + testContext.getName());

        // WebDriverManager.getCurrent().startWebDriver();
    }

    @Override
    public void afterClass(ITestContext testContext) {
        System.out.println(":: After Class " + testContext.getName());

        // WebDriverManager.getCurrent().stopWebDriver();
    }

    @Override
    public void beforeTest(ITestContext testContext) {
        System.out.println(":: Before Test ");
    }

    @Override
    public void afterTest(ITestContext testContext) {
        System.out.println(":: After Test ");
    }

    @Override
    public void beforeMethod(ITestContext testContext, Method method) {
        System.out.println(":: Before Method " + method.getName());
    }

    @Override
    public void afterMethod(ITestContext testContext, ITestResult result) {
        System.out.println(":: After Method " + testContext.getName());
    }
}