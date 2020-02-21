package main.java.com.framework.configuration;

import main.java.com.framework.configuration.model.ApplicationSettings;
import main.java.com.framework.configuration.model.FieldSettings;
import main.java.com.framework.configuration.model.serialization.ApplicationSettingsSerializer;
import main.java.com.framework.configuration.model.serialization.FieldSettingsSerializer;

public final class ConfigurationManager {
    private static final String APPLICATION_SETTINGS_FILEPATH = "application-settings.yml";
    private static final String FIELD_SETTINGS_FILEPATH = "field-settings.yml";

    private ApplicationSettingsSerializer applicationSettingsSerializer;
    private FieldSettingsSerializer fieldSettingsSerializer;

    private ApplicationSettings applicationSettings;
    private FieldSettings fieldSettings;

    public ConfigurationManager(
            ApplicationSettingsSerializer applicationSettingsSerializer,
            FieldSettingsSerializer fieldSettingsSerializer) {
        this.applicationSettingsSerializer = applicationSettingsSerializer;
        this.fieldSettingsSerializer = fieldSettingsSerializer;

        this.applicationSettings = applicationSettingsSerializer.retrieve(APPLICATION_SETTINGS_FILEPATH, true);
        this.fieldSettings = fieldSettingsSerializer.retrieve(FIELD_SETTINGS_FILEPATH, true);
    }

    public ApplicationSettings getApplicationSettings() {
        return this.getApplicationSettings(ConfigurationManager.APPLICATION_SETTINGS_FILEPATH);
    }

    public ApplicationSettings getApplicationSettings(String filename) {
        System.out.println(String.format("Reading configuration file: %s", filename));

        if (filename != null || filename.length() > 0)
            this.applicationSettings = this.applicationSettingsSerializer.retrieve(filename, true);

        return this.applicationSettings;
    }

    public FieldSettings getFieldSettings() {
        return this.getFieldSettings(ConfigurationManager.FIELD_SETTINGS_FILEPATH);
    }

    public FieldSettings getFieldSettings(String filename) {
        if (filename != null || filename.length() > 0)
            this.fieldSettings = this.fieldSettingsSerializer.retrieve(filename, true);

        return this.fieldSettings;
    }
}
