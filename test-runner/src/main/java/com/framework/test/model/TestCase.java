package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestCase {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Result result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finish;

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

    public String getStart() { return this.start; }
    public void setStart(String value) { this.start = value; }

    public String getFinish() { return this.finish; }
    public void setFinish(String value) { this.finish = value; }

    public List<TestStep> getTestStepList() { return this.testStepList; }
    protected void setTestStepList(List<TestStep> value) { this.testStepList = value; }
}