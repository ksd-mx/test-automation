package main.java.com.framework.configuration.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigurationSettingsSerializer {
    public <T> T ReadSettingsFromFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        return mapper.readValue(new File(filename), new TypeReference<T>() {});
    }

    public <T> void WriteSettingsToFile(String filename, T value) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        mapper.writeValue(new File(filename), value);
    }
}
