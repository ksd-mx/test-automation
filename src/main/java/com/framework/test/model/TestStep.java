package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestStep extends DefaultTestArtifact {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String actionPath;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expectation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String outcome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> screenshotPathList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<InputData> inputDataList;

    public TestStep() {
        this.inputDataList = new ArrayList<>();
        this.screenshotPathList = new ArrayList<>();
    }

    public TestStep(String actionPath, String outcome, String expectation) {
        this();

        this.setActionPath(actionPath);
        this.setOutcome(outcome);
        this.setExpectation(expectation);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTestCaseId() { return super.getExternalId(); }
    public void setTestCaseId(String value) { super.setExternalId(value); }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getActionPath() { return this.actionPath; }
    public void setActionPath(String value) { this.actionPath = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getExpectation() { return this.expectation; }
    public void setExpectation(String value) { this.expectation = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getOutcome() { return this.outcome; }
    public void setOutcome(String value) { this.outcome = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getComment() { return this.comment; }
    public void setComment(String value) { this.comment = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getErrorMessage() { return this.errorMessage; }
    public void setErrorMessage(String value) { this.errorMessage = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> getScreenshotList() { return this.screenshotPathList; }
    protected void setScreenshotList(List<String> value) { this.screenshotPathList = value; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<InputData> getInputDataList() { return this.inputDataList; }
    protected void setInputDataList(List<InputData> value) { this.inputDataList = value; }
}