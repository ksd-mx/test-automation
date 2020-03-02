package test.java.com.laboratory.test.implementation;

import main.java.com.framework.test.BaseTestClass;
import main.java.com.framework.test.model.TestCase;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class DefaultTestImpl extends BaseTestClass {

    public DefaultTestImpl(TestCase testCase) {
        super(testCase);
    }

    @Override
    public void beforeClass(ITestContext testContext) {
        System.out.println("Executing BEFORE CLASS method");
    }

    @Override
    public void afterClass(ITestContext testContext) {
        System.out.println("Executing AFTER CLASS method");
    }

    @Override
    public void beforeTest(ITestContext testContext) {
        System.out.println("Executing BEFORE TEST method");
    }

    @Override
    public void afterTest(ITestContext testContext) {
        System.out.println("Executing AFTER TEST method");
    }

    @Override
    public void beforeMethod(ITestContext testContext, Method method) {
        System.out.println("Executing BEFORE METHOD method");
    }

    @Override
    public void afterMethod(ITestContext testContext, ITestResult result) {
        System.out.println("Executing AFTER METHOD method");
    }
}
