package main.framework.test.description.shared.steps;

import main.framework.data.IDataReader;
import main.framework.test.description.StepStrategy;
import main.framework.test.model.Result;
import main.framework.test.model.TestStep;
import main.framework.test.selenium.WebDriverManager;

public class NotExecutedStep extends StepStrategy {
    public NotExecutedStep(WebDriverManager webDriverManager, TestStep testSteps) {
        super(webDriverManager, testSteps);
    }

    @Override
    public void onExecute(IDataReader dataReader) {
        this.setResult(Result.NOTEXECUTED);
    }
}
