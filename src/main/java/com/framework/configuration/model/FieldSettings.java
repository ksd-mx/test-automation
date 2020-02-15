package main.java.com.framework.configuration.model;

import main.java.com.framework.test.model.InputData;
import main.java.com.framework.test.model.PathType;

import java.util.ArrayList;

public final class FieldSettings extends ArrayList<InputData> {
    public static FieldSettings getMock() {
        FieldSettings result = new FieldSettings();

        result.add(new InputData("teste.campo.1", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.2", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.3", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.4", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.5", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.6", "algum//xpth//aqui", PathType.XPATH));
        result.add(new InputData("teste.campo.7", "algum//xpth//aqui", PathType.XPATH));

        return result;
    }
}
