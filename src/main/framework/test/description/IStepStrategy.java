package main.framework.test.description;

import main.framework.data.IDataReader;

public interface IStepStrategy {
    void execute(IDataReader dataReader);
}