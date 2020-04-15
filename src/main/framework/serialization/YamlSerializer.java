package main.framework.serialization;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlSerializer extends ObjectSerializer {
    public YamlSerializer() {
        super(new YAMLFactory());

        this.dataFormat = DataFormat.YAML;
    }
}
