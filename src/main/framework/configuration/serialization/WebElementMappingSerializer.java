package main.framework.configuration.serialization;

import main.framework.configuration.model.selenium.WebElementMappingCollection;
import main.framework.serialization.ObjectSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WebElementMappingSerializer {
    private final ObjectSerializer objectSerializer;

    public WebElementMappingSerializer(ObjectSerializer.DataFormat format) {
        this.objectSerializer = ObjectSerializer.createSerializer(format);
    }

    public WebElementMappingCollection retrieve(String fileName, boolean create) {
        WebElementMappingCollection result = new WebElementMappingCollection();
        try {
            result = (WebElementMappingCollection) this.objectSerializer
                    .ReadSettingsFromFile(
                            WebElementMappingCollection.class,
                            fileName);
            result.refreshKeys();
        } catch (FileNotFoundException e) {
            if (create) {
                this.objectSerializer.WriteSettingsToFile(
                        result,
                        fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
}
