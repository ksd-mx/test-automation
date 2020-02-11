package main.java.com.framework.configuration.model;

import main.java.com.framework.serialization.ObjectSerializer;

public class ApplicationSettings {

    private String targetApplicationLoginUrl;
    private String testRunResultPath;
    private ObjectSerializer.FileType defaultSerializationDataType;

    public ApplicationSettings() {
        this.targetApplicationLoginUrl = "";
        this.testRunResultPath = "results.json";
        this.defaultSerializationDataType = ObjectSerializer.FileType.JSON;
    }

    public String getTargetApplicationLoginUrl() { return this.targetApplicationLoginUrl; }
    public void setTargetApplicationLoginUrl(String value) { this.targetApplicationLoginUrl = value; }

    public String getTestRunResultPath() { return this.testRunResultPath; }
    public void setTestRunResultPath(String value) { this.testRunResultPath = value; }

    public ObjectSerializer.FileType getDefaultSerializationDataType() { return this.defaultSerializationDataType; }
    public void setDefaultSerializationDataType(ObjectSerializer.FileType value) { this.defaultSerializationDataType = value; }

}