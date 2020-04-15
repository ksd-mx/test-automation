package test.implementation;

import main.framework.data.IDataReader;
import main.framework.test.ExecutionContext;
import main.framework.test.selenium.BaseTestSuite;
import test.ApplicationConstants;
import test.implementation.Demonstration.DemonstrationTestCase;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class DemonstrationTestSuite extends BaseTestSuite {
    private final IDataReader localDataReader;

    public DemonstrationTestSuite() throws FileNotFoundException {
        super(ExecutionContext.getCurrent().getLocalServiceCollection());

        this.localDataReader = this.getDatasource(ApplicationConstants.DataFiles.SOME_FILE);
    }

    @Test
    public void ExampleTestCaseInvocationTest() {
        new DemonstrationTestCase(
                ExecutionContext.getCurrent().getLocalServiceCollection(),
                "6309").runTest(this.localDataReader);
    }
}
