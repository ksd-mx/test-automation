package main.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class TestArtifact {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String externalId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int priority;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String start;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finish;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Result result;

    public TestArtifact() {
        this.result = Result.NOTEXECUTED;
    }

    public TestArtifact(String externalId, String name, int priority) {
        this();
        this.setExternalId(externalId);
        this.setName(name);
        this.setPriority(priority);
    }

    public String getExternalId() { return this.externalId; }
    public void setExternalId(String value) { this.externalId = value; }

    public String getName() { return this.name; }
    public void setName(String value) { this.name = value; }

    public int getPriority() { return this.priority; }
    public void setPriority(int value) { this.priority = value; }

    public String getStart() { return this.start; }
    public void setStart(String value) { this.start = value; }

    public String getFinish() { return this.finish; }
    public void setFinish(String value) { this.finish = value; }

    public Result getResult() { return this.result; }
    public void setResult(Result value) { this.result = value; }

}