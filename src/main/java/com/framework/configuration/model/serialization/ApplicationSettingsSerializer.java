package main.java.com.framework.configuration.model.serialization;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.serialization.ObjectSerializer;

import java.io.IOException;

public class ApplicationSettingsSerializer {
    private final ObjectSerializer objectSerializer;

    public ApplicationSettingsSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public ApplicationSettings retrieve(String fileName, boolean create) {
        ApplicationSettings result = new ApplicationSettings();

        try {
            result = (ApplicationSettings) this.objectSerializer
                    .ReadSettingsFromFile(
                            ApplicationSettings.class,
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
