package main.java.com.framework.test.aspects;

import main.java.com.framework.component.IProvider;
import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.graphics.ScreenshotProvider;
import main.java.com.framework.logging.LoggingManager;
import main.java.com.framework.test.*;
import main.java.com.framework.test.model.*;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.logging.*;

@Aspect
public class TestStepAuditAspect {

    private TestPlan currentTestPlan;

    private IProvider screenshotProvider;

    public TestStepAuditAspect() {
        this.currentTestPlan = RunContext.getCurrent().getCurrentTestRun();
        this.screenshotProvider = new ScreenshotProvider();
    }

    /***
     * This method is being used to weave this aspect onto Step implementation classes
     * @param jointPoint An instance of the JointPoint class containing woven method information
     * */
    @Around("@annotation(Step) && execution(* *(..))")
    public void registerTestStepAudit(ProceedingJoinPoint jointPoint) throws Throwable {
        Step stepAnnotation = this.getTestStepAnnotation(jointPoint);

        TestStep step = this.getTestStepFromStep(stepAnnotation);

        String scrBefFn = String.format("SCREENSHOT_BEFORE_%s_%s.png", step.getTestCaseId(), step.getActionPath());
        String scrAftFn = String.format("SCREENSHOT_AFTER_%s_%s.png", step.getTestCaseId(), step.getActionPath());

        if (stepAnnotation.screenshotBefore()) this.TakeScreenshot(step, scrBefFn);
        step.setStart(LocalDateTime.now().toString());

        jointPoint.proceed();

        step.setFinish(LocalDateTime.now().toString());
        if (stepAnnotation.screenshotAfter()) this.TakeScreenshot(step, scrAftFn);
    }

    private void TakeScreenshot(TestStep step, String filename) throws Throwable {
        Path finalPath =
                Paths.get(
                        ConfigurationManager.getCurrent().getApplicationSettings().getResultPath(),
                        String.valueOf(step.getTestCaseId()),
                        step.getActionPath(),
                        filename);

        File screenshotFile = new File(finalPath.toString());

        FileUtils.moveFile(this.screenshotProvider.provide(), screenshotFile);

        step.getScreenshotList().add(finalPath.toString());
    }

    private TestStep getTestStepFromStep(Step stepAnnotation) {
        TestStep thisStep = null;

        for (TestCase tc : this.currentTestPlan.getTestCaseList()) {
            if (tc.getExternalId().equals(stepAnnotation.testCaseId())) {
                for (TestStep step : tc.getTestStepList()) {
                    if (step.getActionPath().equals(stepAnnotation.actionPath())) {
                        step.setTestCaseId(tc.getExternalId());
                        return step;
                    }
                }
            }
        }

        throw new NullPointerException("A step with ActionPath (%s) was not found.");
    }

    private Step getTestStepAnnotation(ProceedingJoinPoint jointPoint) {
        MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();

        Method method = methodSignature.getMethod();

        return method.getAnnotation(Step.class);
    }
}
