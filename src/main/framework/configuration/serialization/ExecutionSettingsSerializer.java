package main.framework.configuration.serialization;

import main.framework.configuration.model.ExecutionSettings;
import main.framework.serialization.ObjectSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExecutionSettingsSerializer {
    private final ObjectSerializer objectSerializer;

    public ExecutionSettingsSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public ExecutionSettings retrieve(String fileName, boolean create) {
        ExecutionSettings result = new ExecutionSettings();

        try {
            result = (ExecutionSettings) this.objectSerializer
                    .ReadSettingsFromFile(
                            ExecutionSettings.class,
                            fileName);
        } catch (FileNotFoundException e) {
            if (create) {
                this.objectSerializer.WriteSettingsToFile(
                        result,
                        fileName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return result;
        }
    }
}
