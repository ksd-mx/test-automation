package main.framework.serialization;

import java.io.IOException;

public interface IObjectSerializer {
    Object ReadSettingsFromFile(Class type, String filename) throws IOException;

    void WriteSettingsToFile(Object value, String filename) throws IOException;
}
