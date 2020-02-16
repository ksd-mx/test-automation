package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.selenium.DriverFactory;

public class ApplicationSettings {

    private DriverFactory.DriverType seleniumDriverType;

    private ObjectSerializer.DataFormat serializerDataFormat;

    private String resultPath;

    public ApplicationSettings() {
        this.seleniumDriverType = DriverFactory.DriverType.CHROME;
        this.serializerDataFormat = ObjectSerializer.DataFormat.JSON;
        this.resultPath = "target//test-automation-results//";
    }

    public DriverFactory.DriverType getSeleniumDriverType() { return this.seleniumDriverType; }
    public void setSeleniumDriverType(DriverFactory.DriverType value) { this.seleniumDriverType = value; }

    public ObjectSerializer.DataFormat getSerializerDataFormat() { return this.serializerDataFormat; }
    public void setSerializerDataFormat(ObjectSerializer.DataFormat value) { this.serializerDataFormat = value; }

    public String getResultPath() { return this.resultPath; }
    public void setResultPath(String value) { this.resultPath = value; }

}