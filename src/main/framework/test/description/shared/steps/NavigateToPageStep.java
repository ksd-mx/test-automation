package main.framework.test.description.shared.steps;

import main.framework.data.IDataReader;
import main.framework.test.description.StepStrategy;
import main.framework.test.model.TestStep;
import main.framework.test.selenium.WebDriverManager;
import main.framework.test.selenium.WebElementHandler;

public class NavigateToPageStep extends StepStrategy {
    private String credentialKey;
    private String pageKey;
    private String confirmationElementKey;

    public NavigateToPageStep(WebDriverManager webDriverManager, TestStep testSteps) {
        super(webDriverManager, testSteps);
    }

    public NavigateToPageStep setCredentialKey(String key) {
        this.credentialKey = key;
        return this;
    }

    public NavigateToPageStep setPageKey(String key) {
        this.pageKey = key;
        return this;
    }

    public NavigateToPageStep setConfirmationElementKey(String key) {
        this.confirmationElementKey = key;
        return this;
    }

    @Override
    public void onExecute(IDataReader dataReader) {
        this.webDriverManager.navigateTo(this.pageKey, this.credentialKey);

        WebElementHandler handler = this.webDriverManager.getWebElement(this.confirmationElementKey);
        // Compares the element content with the mapped value
        handler.assertExists();
        handler.assertValueMatch();
    }
}
