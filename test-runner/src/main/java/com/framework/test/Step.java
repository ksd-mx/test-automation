package main.java.com.framework.test;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Step {

    int testCaseId();

    String actionPath();

}
