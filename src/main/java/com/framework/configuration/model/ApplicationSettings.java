package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.selenium.DriverFactory;

import java.nio.file.Paths;

public class ApplicationSettings {

    private String targetApplicationLoginUrl;

    private String resultPath;

    private String testRunResultFileName;

    public DriverFactory.DriverType defaultSeleniumDriverType;

    private ObjectSerializer.DataFormat defaultSerializationDataType;

    public ApplicationSettings() {
        this.targetApplicationLoginUrl = "";
        this.resultPath = "target//automated-test-results//";
        this.testRunResultFileName = "results.json";
        this.defaultSeleniumDriverType = DriverFactory.DriverType.CHROME;
        this.defaultSerializationDataType = ObjectSerializer.DataFormat.JSON;
    }

    public String getTargetApplicationLoginUrl() { return this.targetApplicationLoginUrl; }
    public void setTargetApplicationLoginUrl(String value) { this.targetApplicationLoginUrl = value; }

    public String getResultPath() { return this.resultPath; }
    public void setResultPath(String value) { this.resultPath = value; }

    public String getTestRunResultFileName() { return this.testRunResultFileName; }
    public void setTestRunResultFileName(String value) { this.testRunResultFileName = value; }

    public String getTestRunResultFilePath() { return Paths.get(this.resultPath, this.testRunResultFileName).toString(); }

    public DriverFactory.DriverType getDefaultSeleniumDriverType() { return this.defaultSeleniumDriverType; }
    public void setDefaultSeleniumDriverType(DriverFactory.DriverType value) { this.defaultSeleniumDriverType = value; }

    public ObjectSerializer.DataFormat getDefaultSerializationDataType() { return this.defaultSerializationDataType; }
    public void setDefaultSerializationDataType(ObjectSerializer.DataFormat value) { this.defaultSerializationDataType = value; }

}