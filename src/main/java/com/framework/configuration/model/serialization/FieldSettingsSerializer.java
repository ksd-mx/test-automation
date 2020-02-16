package main.java.com.framework.configuration.model.serialization;

import main.java.com.framework.configuration.model.FieldSettings;
import main.java.com.framework.serialization.ObjectSerializer;

import java.io.IOException;

public class FieldSettingsSerializer {
    private final ObjectSerializer objectSerializer;

    public FieldSettingsSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public FieldSettings retrieve(String fileName, boolean create) {
        FieldSettings result = FieldSettings.getMock();

        try {
            result = (FieldSettings) this.objectSerializer
                    .ReadSettingsFromFile(
                            FieldSettings.class,
                            fileName);
        } catch (IOException e) {
            if (create) {
                this.objectSerializer.WriteSettingsToFile(
                        result,
                        fileName);
            }
        }
        finally {
            return result;
        }
    }
}
