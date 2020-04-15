package test.implementation.Demonstration.Steps;

import main.framework.test.description.StepStrategy;
import main.framework.test.model.TestStep;
import main.framework.test.selenium.WebDriverManager;

public class ApplicationInitializationStep extends StepStrategy {
    public ApplicationInitializationStep(WebDriverManager webDriverManager, TestStep step) {
        super(webDriverManager, step);
    }
}
