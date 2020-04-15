package main.framework.test.selenium;

import main.framework.configuration.ConfigurationManager;
import main.framework.data.IDataReader;
import main.framework.data.excel.ExcelReader;
import main.framework.services.LocalServiceCollection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTestSuite implements AutoCloseable {
    private final Map<String, IDataReader> dataReaderCache;
    private final LocalServiceCollection localServiceCollection;

    public BaseTestSuite(LocalServiceCollection localServiceCollection) {
        this.localServiceCollection = localServiceCollection;
        this.dataReaderCache = new HashMap<>();
    }

    public IDataReader getDatasource(String datasourceKey) throws FileNotFoundException {
        IDataReader dataReader = this.dataReaderCache.get(datasourceKey);

        if (dataReader == null) {
            try {
                dataReader = new ExcelReader(this.getConfigurationManager(), datasourceKey);

                this.dataReaderCache.put(datasourceKey, dataReader);
            } catch (IOException e) {
                String filePath = this.getConfigurationManager()
                        .getExecutionSettings()
                        .getDatasource(datasourceKey);
                String exceptionMessage = String.format(
                        "COULD NOT LOAD [%s@%s] DATA FILE.",
                        datasourceKey,
                        filePath);
                throw new FileNotFoundException(exceptionMessage);
            }
        }

        return dataReader;
    }

    public ConfigurationManager getConfigurationManager() {
        return this.localServiceCollection.get(ConfigurationManager.SERVICE_KEY);
    }

    public void close() {
        for (IDataReader dataReader : this.dataReaderCache.values()) {
            try {
                dataReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
