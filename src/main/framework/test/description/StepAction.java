package main.framework.test.description;

public final class StepAction {
    private final String mappingKey;
    private final String valueKey;
    private final ActionType actionType;
    private final String overrideValue;
    private final long interval;

    private StepAction(ActionType actionType) {
        this(null, null, actionType, null, -1);
    }

    private StepAction(String mappingKey, ActionType actionType) {
        this(mappingKey, null, actionType, null, -1);
    }

    private StepAction(String mappingKey, ActionType actionType, long interval) {
        this(mappingKey, null, actionType, null, interval);
    }

    private StepAction(String mappingKey, String valueKey, ActionType actionType) {
        this(mappingKey, valueKey, actionType, null, -1);
    }

    private StepAction(String mappingKey, ActionType actionType, String overrideValue) {
        this(mappingKey,  null, actionType, overrideValue, -1);
    }

    private StepAction(String mappingKey, String valueKey, ActionType actionType, String overrideValue, long interval) {
        this.mappingKey = mappingKey;
        this.valueKey = valueKey;
        this.actionType = actionType;
        this.overrideValue = overrideValue;
        this.interval = interval;
    }

    public String getMappingKey() { return this.mappingKey; }
    public String getValueKey() { return this.valueKey; }
    public ActionType getActionType() { return this.actionType; }
    public String getOverrideValue() { return this.overrideValue; }
    public long getInterval() { return this.interval; }

    public static StepAction create(ActionType actionType) {
        return new StepAction(actionType);
    }

    public static StepAction create(String mappingKey, ActionType actionType) {
        return new StepAction(mappingKey, actionType);
    }

    public static StepAction create(String mappingKey, ActionType actionType, long interval) {
        return new StepAction(mappingKey, actionType, interval);
    }

    public static StepAction create(String mappingKey, String valueKey, ActionType actionType) {
        return new StepAction(mappingKey, valueKey, actionType);
    }

    public static StepAction create(String mappingKey, ActionType actionType, String overrideValue) {
        return new StepAction(mappingKey, actionType, overrideValue);
    }

    public static StepAction create(String mappingKey, String valueKey, ActionType actionType, long interval) {
        return new StepAction(mappingKey, valueKey, actionType, null, interval);
    }

    public static StepAction create(String mappingKey, ActionType actionType, String overrideValue, long interval) {
        return new StepAction(mappingKey, null, actionType, overrideValue, interval);
    }
}
