package main.java.com.framework.serialization;

import com.fasterxml.jackson.core.JsonFactory;

public class JsonSerializer extends ObjectSerializer {
    public JsonSerializer() {
        super(new JsonFactory());
    }
}
