package main.framework.serialization;

public interface IFormatter<T> {
    <T> T format(String value);
}
