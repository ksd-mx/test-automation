package main.java.com.framework.test.aspects;

import main.java.com.framework.test.*;
import main.java.com.framework.test.model.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.time.LocalDateTime;

@Aspect
public class TestStepAuditAspect {

    @Around("@annotation(Step)")
    public Object registerTestStepAudit(ProceedingJoinPoint jointPoint) throws Throwable {
        System.out.println("#################### Executing aspect for test steps");

        TestStep step = this.getTestStepFromJoinPoint(jointPoint);

        step.setStart(LocalDateTime.now().toString());

        Object result = jointPoint.proceed();

        step.setFinish(LocalDateTime.now().toString());

        RunContext.getCurrent().save();

        return result;
    }

    private TestStep getTestStepFromJoinPoint(ProceedingJoinPoint jointPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();

        Step stepAnnotation = methodSignature.getMethod().getAnnotation(Step.class);

        TestStep thisStep = null;

        for (TestCase tc : RunContext.getCurrent().getCurrentTestRun().getTestCaseList()) {
            System.out.println(String.format("TC ID: %s", tc.getId()));
            if (tc.getId() == stepAnnotation.testCaseId()) {
                for (TestStep step : tc.getTestStepList()) {
                    System.out.println(String.format("TS ID: %s", step.getAction()));
                    System.out.println(String.format("SA ID: %s", stepAnnotation.actionPath()));
                    if (step.getAction() .equals(stepAnnotation.actionPath())) {
                        System.out.println(String.format("ACHEI ID: %s", step.getAction()));
                        return step;
                    }
                }
            }
        }

        if (thisStep == null) throw new NullPointerException("A step with ActionPath (%s) was not found.");

        return thisStep;
    }
}
