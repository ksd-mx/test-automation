package main.java.com.framework.configuration.model;

import main.java.com.framework.test.model.TestField;
import main.java.com.framework.test.model.PathType;

import java.util.ArrayList;

public final class FieldSettings extends ArrayList<TestField> {
    public static FieldSettings getMock() {
        FieldSettings result = new FieldSettings();

        result.add(new TestField("teste.campo.1", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.2", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.3", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.4", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.5", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.6", "algum//xpth//aqui", PathType.XPATH));
        result.add(new TestField("teste.campo.7", "algum//xpth//aqui", PathType.XPATH));

        return result;
    }
}
