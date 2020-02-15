package main.java.com.framework.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class InputData {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String key;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PathType pathType;

    public InputData(String key, String path, PathType pathType) {
        this.setKey(key);
        this.setPath(path);
        this.setPathType(pathType);
    }

    public String getKey() { return this.key; }
    public void setKey(String value) { this.key = value; }

    public String getValue() { return this.value; }
    public void setValue(String value) { this.value = value; }

    public String getPath() { return this.path; }
    public void setPath(String value) { this.path = value; }

    public PathType getPathType() { return this.pathType; }
    public void setPathType(PathType value) { this.pathType = value; }
}