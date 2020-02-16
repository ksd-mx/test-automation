package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class TestStep extends TestArtifact {
    @JsonIgnore
    private TestCase testCase;
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
    private List<TestField> testFieldList;

    public TestStep() {
        this.testFieldList = new ArrayList<>();
        this.screenshotPathList = new ArrayList<>();
    }

    public TestStep(TestCase testCase, String actionPath, String outcome, String expectation) {
        this();

        this.setTestCase(testCase);
        this.setActionPath(actionPath);
        this.setOutcome(outcome);
        this.setExpectation(expectation);
    }

    @JsonIgnore
    public TestCase getTestCase() { return this.testCase; }
    public void setTestCase(TestCase value) { this.testCase = value; }

    @Override
    @JsonIgnore
    public String getExternalId() { return String.format("%s_%s", this.testCase.getExternalId(), this.actionPath); }
    @Override
    public void setExternalId(String value) { super.setExternalId(value); }

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
    public List<TestField> getTestFieldList() { return this.testFieldList; }
    protected void setTestFieldList(List<TestField> value) { this.testFieldList = value; }
}