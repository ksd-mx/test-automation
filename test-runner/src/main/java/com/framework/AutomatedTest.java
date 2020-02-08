package main.java.com.framework;

import org.testng.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class AutomatedTest {

    private ITestContext testContext;

    @BeforeClass
    public void onBeforeClass(final ITestContext textContext) {
        this.testContext = textContext;

        System.out.println(":: Before Class " + this.testContext.getName());
    }

    @AfterClass
    public void onAfterClass() {
        System.out.println(":: After Class " + this.testContext.getName());
    }

    @BeforeTest
    public void onBeforeTest() {
        System.out.println(":: Before Test ");
    }

    @AfterTest
    public void onAfterTest() {
        System.out.println(":: After Test ");
    }

    @BeforeMethod
    public void onBeforeMethod(Method method) {
        System.out.println(":: Before Method " + method.getName());
    }

    @AfterMethod
    public void onAfterMethod(ITestResult result) {
        System.out.println(":: After Method " + this.testContext.getName());
    }

}