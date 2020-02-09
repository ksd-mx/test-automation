package main.java.com.framework.test.model;

public final class InputData {
    private String key;
    private String value;
    private String path;
    private PathType pathType;

    public String getKey() { return this.key; }
    public void setKey(String value) { this.key = value; }

    public String getValue() { return this.value; }
    public void setValue(String value) { this.value = value; }

    public String getPath() { return this.path; }
    public void setPath(String value) { this.path = value; }

    public PathType getPathType() { return this.pathType; }
    public void setPathType(PathType value) { this.pathType = value; }
}