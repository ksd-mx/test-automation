package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.ObjectSerializer;
import main.java.com.framework.test.selenium.DriverFactory;

import java.util.HashMap;

public class ApplicationSettings {

    private DriverFactory.DriverType seleniumDriverType;

    private ObjectSerializer.DataFormat serializerDataFormat;

    private String resultPath;

    private String automationTag;

    private HashMap<String, String> assertMessages;

    public ApplicationSettings() {
        this.seleniumDriverType = DriverFactory.DriverType.CHROME;
        this.serializerDataFormat = ObjectSerializer.DataFormat.JSON;
        this.resultPath = "target//test-automation-results//";
        // data-testautomation by default
        this.automationTag = "data-testautomation";

        this.assertMessages = new HashMap<>();
        assertMessages.put("StringAssertMessage","Element with xpath \"%s\" does not have the expected text:\nActual:%s\nExpected:%s\n");
        assertMessages.put("ElementNotFoundMessage","Element not found: \"%s\" not found by %s: \"%s\"");
    }

    public DriverFactory.DriverType getSeleniumDriverType() { return this.seleniumDriverType; }
    public void setSeleniumDriverType(DriverFactory.DriverType value) { this.seleniumDriverType = value; }

    public ObjectSerializer.DataFormat getSerializerDataFormat() { return this.serializerDataFormat; }
    public void setSerializerDataFormat(ObjectSerializer.DataFormat value) { this.serializerDataFormat = value; }

    public String getResultPath() { return this.resultPath; }
    public void setResultPath(String value) { this.resultPath = value; }

    public String getAutomationTag() {
        return automationTag;
    }
    public void setAutomationTag(String automationTag) {
        this.automationTag = automationTag;
    }

    public HashMap<String, String> getAssertMessages() {
        return assertMessages;
    }
    public void setAssertMessages(HashMap<String, String> assertMessages) {
        this.assertMessages = assertMessages;
    }
}