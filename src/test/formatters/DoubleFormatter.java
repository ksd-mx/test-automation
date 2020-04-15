package test.formatters;

import main.framework.serialization.IFormatter;

public class DoubleFormatter implements IFormatter<Double> {
    @Override
    public Double format(String value) {
        return Double.parseDouble(value);
    }
}
