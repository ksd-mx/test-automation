package main.framework.test.mappings;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class WebElementMapping {
    private final static String DEFAULT_VALUE_KEY = "default";

    @JsonIgnore
    private String key;
    @JsonProperty("values")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> values;
    @JsonProperty("path")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

    public WebElementMapping() {
        this.values = new HashMap<>();
    }

    public WebElementMapping(String path, String value) {
        this();
        this.path = path;
        this.setValue(value);
    }

    public String getKey() { return this.key; }
    public String getPath() { return this.path; }
    public String getValue() { return this.getValue(DEFAULT_VALUE_KEY); }
    public String getValue(String key) { return this.values.get(key); }

    public void setKey(String key) { this.key = key; }
    public void setValue(String value) { this.setValue(DEFAULT_VALUE_KEY, value); }
    public void setValue(String key, String value) { this.values.put(key, value); }
}