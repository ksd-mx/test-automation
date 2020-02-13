package main.java.com.framework.test.aspects;

import main.java.com.framework.component.IProvider;
import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.graphics.ScreenshotProvider;
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
import java.util.UUID;

@Aspect
public class TestStepAuditAspect {

    private TestRun currentTestRun;

    private IProvider screenshotProvider;

    public TestStepAuditAspect() {
        this.currentTestRun = RunContext.getCurrent().getCurrentTestRun();
        this.screenshotProvider = new ScreenshotProvider();
    }

    /***
     * This method is being used to weave this aspect onto Step implementation classes
     * @param jointPoint An instance of the JointPoint class containing woven method information
     * */
    @Around("@annotation(Step) && execution(* *(..))")
    public void registerTestStepAudit(ProceedingJoinPoint jointPoint) throws Throwable {

        TestStep step = this.getTestStepFromJoinPoint(jointPoint);

        String scrBefFn = String.format("SCREENSHOT_BEFORE_%s_%s.png", step.getTestCaseId(), step.getAction());
        String scrAftFn = String.format("SCREENSHOT_AFTER_%s_%s.png", step.getTestCaseId(), step.getAction());

        step.getScreenshotList().add(this.TakeScreenshot(step, scrBefFn));
        step.setStart(LocalDateTime.now().toString());

        jointPoint.proceed();

        step.setFinish(LocalDateTime.now().toString());
        step.getScreenshotList().add(this.TakeScreenshot(step, scrAftFn));

        System.out.println(String.format("### Adding screenshot to Step %s of Test Case %s", step.getAction(), step.getTestCaseId()));
    }

    private String TakeScreenshot(TestStep step, String filename) throws Throwable {
        Path finalPath =
                Paths.get(
                        ConfigurationManager.getCurrent().getApplicationSettings().getResultPath(),
                        String.valueOf(step.getTestCaseId()),
                        step.getAction(),
                        filename);

        File screenshotFile = new File(finalPath.toString());

        FileUtils.moveFile(this.screenshotProvider.provide(), screenshotFile);

        return finalPath.toString();
    }

    private TestStep getTestStepFromJoinPoint(ProceedingJoinPoint jointPoint) {
        TestStep thisStep = null;

        MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();

        Method method = methodSignature.getMethod();

        Step stepAnnotation = method.getAnnotation(Step.class);

        for (TestCase tc : this.currentTestRun.getTestCaseList()) {
            if (tc.getId() == stepAnnotation.testCaseId()) {
                for (TestStep step : tc.getTestStepList()) {
                    if (step.getAction().equals(stepAnnotation.actionPath())) {
                        step.setTestCaseId(tc.getId());
                        return step;
                    }
                }
            }
        }

        if (thisStep == null) throw new NullPointerException("A step with ActionPath (%s) was not found.");

        return thisStep;
    }
}
