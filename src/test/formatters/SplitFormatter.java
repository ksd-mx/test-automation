package test.formatters;

import main.framework.serialization.IFormatter;

public class SplitFormatter implements IFormatter<String> {
    private final String splitChar;
    private final int positionIndex;

    public SplitFormatter(String splitChar, int positionIndex) {
        this.splitChar = splitChar;
        this.positionIndex = positionIndex;
    }

    @Override
    public String format(String value) {
        return value.split(this.splitChar)[this.positionIndex];
    }
}
