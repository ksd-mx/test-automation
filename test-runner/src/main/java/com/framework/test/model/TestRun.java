package main.java.com.framework.test.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestRun {
    private LocalDateTime start;
    private LocalDateTime finish;
    private List<TestCase> testCaseList;

    public TestRun() {
        this.testCaseList = new ArrayList<>();
    }

    public LocalDateTime getStart() { return this.start; }
    public void setStart(LocalDateTime value) { this.start = value; }

    public LocalDateTime getFinish() { return this.finish; }
    public void setFinish(LocalDateTime value) { this.finish = value; }

    public List<TestCase> getTestCaseList() { return this.testCaseList; }
    protected void setTestCaseList(List<TestCase> value) { this.testCaseList = value; }
}