package main.java.com.framework.configuration.model;

import main.java.com.framework.test.model.PathType;
import main.java.com.framework.test.model.TestField;

import java.util.HashMap;

public final class FieldSettings extends HashMap<String, TestField> {

    public static FieldSettings getMock() {
        FieldSettings result = new FieldSettings();

        result.put("teste.campo.1", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.2", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.3", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.4", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.5", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.6", new TestField("algum//xpth//aqui", PathType.XPATH));
        result.put("teste.campo.7", new TestField("algum//xpth//aqui", PathType.XPATH));

        return result;
    }
}
