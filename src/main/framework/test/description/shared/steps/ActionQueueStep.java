package main.framework.test.description.shared.steps;

import main.framework.data.IDataReader;
import main.framework.test.description.StepAction;
import main.framework.test.description.StepStrategy;
import main.framework.test.model.TestStep;
import main.framework.test.selenium.WebDriverManager;
import main.framework.test.selenium.WebElementHandler;

import java.util.LinkedList;
import java.util.Queue;

public final class ActionQueueStep extends StepStrategy {
    public ActionQueueStep(WebDriverManager webDriverManager, TestStep testSteps) {
        super(webDriverManager, testSteps);
    }

    @Override
    public void onExecute(IDataReader dataReader) {
        this.executeQueuedActions(dataReader);
    }
}