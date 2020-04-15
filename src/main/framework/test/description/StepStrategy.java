package main.framework.test.description;

import main.framework.data.IDataReader;
import main.framework.test.model.Result;
import main.framework.test.model.TestStep;
import main.framework.test.selenium.WebDriverManager;
import main.framework.test.selenium.WebElementHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public abstract class StepStrategy implements IStepStrategy {
    private final Queue<StepAction> stepActionQueue;

    protected WebDriverManager webDriverManager;
    protected TestStep step;

    public StepStrategy(WebDriverManager webDriverManager, TestStep step) {
        this.webDriverManager = webDriverManager;
        this.step = step;
        this.stepActionQueue = new LinkedList<>();
    }

    public final void execute(IDataReader dataReader) {
        System.out.println(String.format("STARTING STEP '%s'.", this.getClass().getName()));
        try {
            this.step.setStart(LocalDateTime.now().toString());
            this.onExecute(dataReader);
            this.step.setFinish(LocalDateTime.now().toString());
            this.takeScreenshot(this.step, "AFTER");
            this.step.setResult(Result.PASSED);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.step.setFinish(LocalDateTime.now().toString());
            this.step.setResult(Result.FAILED);
            this.takeScreenshot(this.step, "FAILURE");

            throw ex;
        }
    }

    public void onExecute(IDataReader dataReader) {
        this.executeQueuedActions(dataReader);
    }

    protected void setResult(Result result) {
        this.step.setResult(result);
    }

    protected void takeScreenshot(TestStep step, String suffix) {
        try {
            this.webDriverManager.takeScreenshot(step, suffix);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public StepStrategy enqueue(StepAction action) {
        this.stepActionQueue.add(action);
        return this;
    }

    protected void executeQueuedActions(IDataReader dataReader) {
        while(!this.stepActionQueue.isEmpty()) {
            StepAction action = this.stepActionQueue.remove();
            switch (action.getActionType()) {
                case AUTHENTICATE:
                    this.authenticateTo(action);
                    break;
                case NAVIGATE:
                    this.navigateTo(action);
                    break;
                case CLICK:
                    this.click(action);
                    break;
                case READ:
                    this.read(action);
                    break;
                case ASSIGN:
                    this.assign(action);
                    break;
                case TAKE_SCREENSHOT:
                    this.takeScreenshot(action);
                    break;
                case DATASOURCE_ASSIGN:
                    this.dataSourceAssign(action, dataReader);
                    break;
                case CONFIRM_ASSIGNMENT:
                    this.confirmAssignment(action);
                    break;
                case CONFIRM_MATCH:
                    this.confirmMatch(action);
                    break;
                case WAITVISIBLE:
                    this.waitVisible(action);
                    break;
                case ENSUREPAGELOADED:
                    this.ensurePageLoaded(action);
                    break;
                case WAITINVISIBLE:
                    this.waitInvisible(action);
                    break;
            }
        }
    }

    private void authenticateTo(StepAction action) {
        this.webDriverManager.navigateTo(action.getMappingKey(), action.getOverrideValue());
        this.pause(action.getInterval());
    }

    private void navigateTo(StepAction action) {
        this.webDriverManager.navigateTo(action.getMappingKey(), null);
        this.pause(action.getInterval());
    }

    private void click(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).click();
        this.pause(action.getInterval());
    }

    private void read(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).updateMappingValue();
        this.pause(action.getInterval());
    }

    private void assign(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).resetValue();
        this.pause(action.getInterval());
    }

    private void takeScreenshot(StepAction action) {
        this.takeScreenshot(this.step, action.getActionType().toString());
        this.pause(action.getInterval());
    }

    private void dataSourceAssign(StepAction action, IDataReader dataReader) {
        WebElementHandler handler = this.webDriverManager.getWebElement(action.getMappingKey());
        handler.resetValue(dataReader.read(handler.getMappedValue(action.getValueKey())));
        this.pause(action.getInterval());
    }

    private void confirmAssignment(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).assertValueMatch(action.getOverrideValue());
        this.pause(action.getInterval());
    }

    private void confirmMatch(StepAction action) {
        WebElementHandler handler = this.webDriverManager.getWebElement(action.getMappingKey());
        handler.confirmValue();
        handler.assertValueMatch();
        this.pause(action.getInterval());
    }

    private void waitVisible(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).waitUntilVisible();
        this.pause(action.getInterval());
    }

    private void ensurePageLoaded(StepAction action) {
        this.webDriverManager.ensurePageLoaded();
        this.pause(action.getInterval());
    }

    private void waitInvisible(StepAction action) {
        this.webDriverManager.getWebElement(action.getMappingKey()).waitUntilInvisible();
        this.pause(action.getInterval());
    }

    private void pause(long interval) {
        if (interval > 0)
            this.webDriverManager.pause(interval);
    }
}