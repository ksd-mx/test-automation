package main.java.com.framework.test;

import main.java.com.framework.test.model.TestRun;
import main.java.com.framework.test.model.serialization.TestRunSerializer;

import java.io.IOException;

public class RunContext {

    private static RunContext current;

    private TestRun run;

    private RunContext() throws IOException {
        this.run = TestRunSerializer.ReadSettingsFromFile("testrun.yaml");
    }

    public static RunContext getCurrent() throws IOException {
        if (RunContext.current == null)
            RunContext.current = new RunContext();

        return RunContext.current;
    }

    public TestRun getCurrentTestRun() {
        return this.run;
    }

}
