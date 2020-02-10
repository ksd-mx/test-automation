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
        TestStep step = this.getTestStepFromJoinPoint(jointPoint);

        step.setStart(LocalDateTime.now());

        Object result = jointPoint.proceed();

        step.setFinish(LocalDateTime.now());

        return result;
    }

    private TestStep getTestStepFromJoinPoint(ProceedingJoinPoint jointPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();

        Step stepAnnotation = methodSignature.getMethod().getAnnotation(Step.class);

        TestStep thisStep = null;

        for (TestCase tc : RunContext.getCurrent().getCurrentTestRun().getTestCaseList()) {
            if (tc.getId() == stepAnnotation.testCaseId()) {
                for (TestStep step : tc.getTestStepList()) {
                    if (step.getAction() == stepAnnotation.actionPath()) {
                        thisStep = step;
                    }
                    break;
                }
            }
            if (thisStep != null) {
                break;
            }
        }

        if (thisStep == null) throw new NullPointerException("A step with ActionPath (%s) was not found.");

        return thisStep;
    }
}
