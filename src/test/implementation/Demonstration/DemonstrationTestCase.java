package test.implementation.Demonstration;

import main.framework.data.IDataReader;
import main.framework.services.LocalServiceCollection;
import main.framework.test.selenium.BaseTestCase;
import test.implementation.Demonstration.Steps.ApplicationInitializationStep;

public class DemonstrationTestCase extends BaseTestCase {
    public DemonstrationTestCase(LocalServiceCollection localServiceCollection, String testCaseId) {
        super(localServiceCollection, testCaseId);
    }

    @Override
    protected void onRunTest(IDataReader dataReader) throws Exception {
        this.getStep("12345", ApplicationInitializationStep.class).execute(dataReader);
    }
}
