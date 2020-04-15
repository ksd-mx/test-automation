package main.framework.serialization;

public class StringFormatter implements IFormatter<String> {
    @Override
    public String format(String value) {
        return value;
    }
}