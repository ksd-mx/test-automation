package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestRun {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finish;
    private List<TestCase> testCaseList;

    public TestRun() {
        this.testCaseList = new ArrayList<>();
    }

    public String getStart() { return this.start; }
    public void setStart(String value) { this.start = value; }

    public String getFinish() { return this.finish; }
    public void setFinish(String value) { this.finish = value; }

    public List<TestCase> getTestCaseList() { return this.testCaseList; }
    protected void setTestCaseList(List<TestCase> value) { this.testCaseList = value; }
}