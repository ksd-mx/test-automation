package main.java.com.framework.configuration;

public class EnvironmentContext {

    public String getBuildDirectory() {
        return this.getEnvironmentVariableValue("BUILD_REPOSITORY_LOCALPATH");
    }

    public String getTestResultDirectory() {
        return this.getEnvironmentVariableValue("COMMON_TESTRESULTSDIRECTORY");
    }

    public String getBuildUri() {
        return this.getEnvironmentVariableValue("BUILD_BUILDURI");
    }

    public String getTeamProjectId() {
        return this.getEnvironmentVariableValue("SYSTEM_TEAMPROJECTID");
    }

    public String getCollectionUri() {
        return this.getEnvironmentVariableValue("SYSTEM_COLLECTIONURI");
    }

    private String getEnvironmentVariableValue(String keyName) {
        String value = System.getenv("BUILD_BUILDURI");

        if (value == null || value.trim().length() == 0) {
            throw new NullPointerException("Invalid Environment Variable Key Name: " + keyName);
        }

        return value;
    }
}
