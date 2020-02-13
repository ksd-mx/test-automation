package main.java.com.framework.test;

import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.test.model.TestRun;
import main.java.com.framework.test.model.serialization.TestRunSerializer;
import main.java.com.framework.test.selenium.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class RunContext {

    private static RunContext current;

    private TestRun run;

    private RunContext() {
        this.setCurrentTestRun(new TestRunSerializer().retrieve(ConfigurationManager.TESTRUN_SETTINGS_FILEPATH, true));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                this.save();

                WebDriverManager.getCurrent().stopWebDriver();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }));
    }

    public synchronized TestRun getCurrentTestRun() {
        return this.run;
    }

    public synchronized void setCurrentTestRun(TestRun value) {
        this.run  = value;
    }

    public static synchronized RunContext getCurrent() {
        if (RunContext.current == null)
            RunContext.current = new RunContext();

        return RunContext.current;
    }

    public void save() throws Throwable {
        String resultFile = ConfigurationManager.getCurrent().getApplicationSettings().getTestRunResultFilePath();

        TestRunSerializer testRunSerializer = new TestRunSerializer();

        testRunSerializer.save(this.getCurrentTestRun(), resultFile);
    }
}
