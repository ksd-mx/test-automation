package main.java.com.framework.test;

public class RunContext {

    private static RunContext current;

    public static RunContext getCurrent() {
        if (RunContext.current == null)
            RunContext.current = new RunContext();

        return RunContext.current;
    }

}
