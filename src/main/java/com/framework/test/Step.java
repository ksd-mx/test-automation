package main.java.com.framework.test;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Step {

    String testCaseId();

    String actionPath();

    boolean screenshotBefore() default true;

    boolean screenshotAfter() default true;

}
