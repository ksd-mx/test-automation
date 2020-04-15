package main.framework.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class ObjectSerializer implements IObjectSerializer {
    private JsonFactory factory;

    protected DataFormat dataFormat;

    protected ObjectSerializer(JsonFactory factory) {
        this.factory = factory;
    }

    public enum DataFormat {
        JSON,
        YAML
    }

    public Object ReadSettingsFromFile(Class type, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(this.factory);

        filename = this.addFileExtension(this.dataFormat, filename);

        return mapper.readValue(new File(filename), type);
    }

    public void WriteSettingsToFile(Object value, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper(this.factory);

        filename = this.addFileExtension(this.dataFormat, filename);

        mapper.findAndRegisterModules();

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), value);
    }

    public static ObjectSerializer createSerializer(DataFormat fileType) {
        ObjectSerializer result = null;
        switch(fileType) {
            case JSON:
                result = new JsonSerializer();
                break;
            case YAML:
                result = new YamlSerializer();
                break;
        }
        return result;
    }

    private String addFileExtension(DataFormat dataFormat, String filename) {
        String extension = "%s.json";

        if (dataFormat == DataFormat.YAML)
            extension = "%s.yml";

        return String.format(extension, filename);
    }
}
