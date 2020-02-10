package main.java.com.framework.test.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import main.java.com.framework.test.model.TestRun;

import java.io.File;
import java.io.IOException;

public class TestRunSerializer {
    public static TestRun ReadSettingsFromFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        return mapper.readValue(new File(filename), TestRun.class);
    }

    public static void WriteSettingsToFile(String filename, TestRun value) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        mapper.findAndRegisterModules();

        mapper.writeValue(new File(filename), value);
    }
}
