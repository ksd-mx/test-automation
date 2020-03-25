package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TestField {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PathType pathType;

    public TestField() {
    }

    public TestField(String path, PathType pathType) {
        this();
        this.setPath(path);
        this.setPathType(pathType);
    }

    public String getValue() { return this.value; }
    public void setValue(String value) { this.value = value; }

    public String getPath() { return this.path; }
    public void setPath(String value) { this.path = value; }

    public PathType getPathType() { return this.pathType; }
    public void setPathType(PathType value) { this.pathType = value; }
}