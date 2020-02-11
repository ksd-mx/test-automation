package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestStep {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String action;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expectation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String outcome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String screenshotImagePath;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Result result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finish;

    private List<InputData> inputDataList;

    public TestStep() {
        this.inputDataList = new ArrayList<>();
    }

    public TestStep(String action, String outcome, String expectation) {
        this();

        this.setAction(action);
        this.setOutcome(outcome);
        this.setExpectation(expectation);
    }

    public String getAction() { return this.action; }
    public void setAction(String value) { this.action = value; }

    public String getExpectation() { return this.expectation; }
    public void setExpectation(String value) { this.expectation = value; }

    public String getOutcome() { return this.outcome; }
    public void setOutcome(String value) { this.outcome = value; }

    public String getComment() { return this.comment; }
    public void setComment(String value) { this.comment = value; }

    public String getErrorMessage() { return this.errorMessage; }
    public void setErrorMessage(String value) { this.errorMessage = value; }

    public String getScreenshotImagePath() { return this.screenshotImagePath; }
    public void setScreenshotImagePath(String value) { this.screenshotImagePath = value; }

    public Result getResult() { return this.result; }
    public void setResult(Result value) { this.result = value; }

    public String getStart() { return this.start; }
    public void setStart(String value) { this.start = value; }

    public String getFinish() { return this.finish; }
    public void setFinish(String value) { this.finish = value; }

    public List<InputData> getInputDataList() { return this.inputDataList; }
    protected void setInputDataList(List<InputData> value) { this.inputDataList = value; }
}