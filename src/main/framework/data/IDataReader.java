package main.framework.data;

import main.framework.serialization.IFormatter;

public interface IDataReader extends AutoCloseable {
    String read(String valueAddress);

    <T> T read(String valueAddress, IFormatter<T> formatter);
}
