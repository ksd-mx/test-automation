package main.java.com.framework.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlSerializer {
    public static Object ReadSettingsFromFile(Class type, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        return mapper.readValue(new File(filename), type);
    }

    public static void WriteSettingsToFile(Class value, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        mapper.findAndRegisterModules();

        mapper.writeValue(new File(filename), value);
    }
}
