package main.java.com.framework.test;

import com.google.common.io.Files;
import main.java.com.framework.configuration.model.ConfigurationManager;
import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.model.TestRun;

import java.io.File;
import java.io.IOException;

public class RunContext {

    private static RunContext current;

    private ObjectSerializer objectSerializer;

    private TestRun run;

    private RunContext() throws Throwable {
        String resultPath = ConfigurationManager.getCurrent().getApplicationSettings().getTestRunResultPath();

        this.objectSerializer =
                ObjectSerializer
                        .createSerializer(
                                ConfigurationManager
                                        .getCurrent()
                                        .getApplicationSettings()
                                        .getDefaultSerializationDataType());

        try {
            this.run = (TestRun)
                    this.objectSerializer
                            .ReadSettingsFromFile(
                                    TestRun.class,
                                    resultPath);
        } catch (IOException e) {
            this.objectSerializer
                    .WriteSettingsToFile(
                            ConfigurationManager.getCurrent().getTestRun(),
                            resultPath);
        }
    }

    public static RunContext getCurrent() throws Throwable {
        if (RunContext.current == null)
            RunContext.current = new RunContext();

        return RunContext.current;
    }

    public TestRun getCurrentTestRun() {
        return this.run;
    }

    public void save() {
        String resultPath = ConfigurationManager.getCurrent().getApplicationSettings().getTestRunResultPath();

        File resultFile = new File(resultPath);

        System.out.println(resultFile.getAbsolutePath());
        System.out.println(resultFile.getAbsoluteFile());

        try {
            Files.touch(resultFile);
            this.objectSerializer
                    .WriteSettingsToFile(
                            this.getCurrentTestRun(),
                            resultPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Results written to %s", resultPath));
    }

}
