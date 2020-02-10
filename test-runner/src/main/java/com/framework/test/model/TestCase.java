package main.java.com.framework.test.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCase {
    private int id;
    private String name;
    private Result result;
    private LocalDateTime start;
    private LocalDateTime finish;
    private List<TestStep> testStepList;

    public TestCase() {
        this.testStepList = new ArrayList<>();
    }

    public TestCase(int id, String name) {
        this();

        this.setId(id);
        this.setName(name);
    }

    public int getId() { return this.id; }
    public void setId(int value) { this.id = value; }

    public String getName() { return this.name; }
    public void setName(String value) { this.name = value; }

    public Result getResult() { return this.result; }
    public void setResult(Result value) { this.result = value; }

    public LocalDateTime getStart() { return this.start; }
    public void setStart(LocalDateTime value) { this.start = value; }

    public LocalDateTime getFinish() { return this.finish; }
    public void setFinish(LocalDateTime value) { this.finish = value; }

    public List<TestStep> getTestStepList() { return this.testStepList; }
    protected void setTestStepList(List<TestStep> value) { this.testStepList = value; }
}