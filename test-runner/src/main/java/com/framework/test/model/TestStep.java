package main.java.com.framework.test.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestStep {
    private int id;
    private String action;
    private String expectation;
    private String outcome;
    private String comment;
    private String errorMessage;
    private String screenshotImagePath;
    private Result result;
    private LocalDateTime start;
    private LocalDateTime finish;
    private List<InputData> inputDataList;

    public TestStep() {
        this.inputDataList = new ArrayList<>();
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

    public LocalDateTime getStart() { return this.start; }
    public void setStart(LocalDateTime value) { this.start = value; }

    public LocalDateTime getFinish() { return this.finish; }
    public void setFinish(LocalDateTime value) { this.finish = value; }

    public List<InputData> getInputDataList() { return this.inputDataList; }
    protected void setInputDataList(List<InputData> value) { this.inputDataList = value; }
}