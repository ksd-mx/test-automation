package main.java.com.framework.test.description;

import main.java.com.framework.configuration.ConfigurationManager;
import main.java.com.framework.test.model.InputData;
import main.java.com.framework.test.model.TestStep;
import org.openqa.selenium.NotFoundException;

public abstract class StepStrategy implements IStepStrategy {
    private TestStep step;

    public StepStrategy(TestStep step) {
        this.step = step;
    }

    public abstract void execute();

    protected void setFieldValue(String key, String value) {
        InputData fieldSetting = null;
        InputData fieldTracking = null;

        for (InputData item : ConfigurationManager.getCurrent().getFieldSettings()) {
            if (item.getKey().equals(key)) {
                fieldSetting = item;
                break;
            }
        }

        if (fieldSetting == null) throw new NotFoundException("Field %s has no definition set up.");

        for (InputData stepField : this.step.getInputDataList()) {
            if (stepField.getKey().equals(fieldSetting.getKey())) {
                fieldTracking = stepField;
                break;
            }
        }

        if (fieldTracking == null)
            fieldTracking = new InputData(key, fieldSetting.getPath(), fieldSetting.getPathType());

        fieldTracking.setValue(value);
    }
}
