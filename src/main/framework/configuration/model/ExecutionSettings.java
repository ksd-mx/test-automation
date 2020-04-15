package main.framework.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import main.framework.configuration.model.security.CredentialItem;
import main.framework.serialization.ObjectSerializer;
import main.framework.test.selenium.WebDriverManager;

import java.net.URISyntaxException;
import java.util.HashMap;

public class ExecutionSettings {
    public static final String EXECUTION_SETTINGS_FILEPATH = "execution-settings";
    public static final String FIELD_SETTINGS_FILEPATH = "webelement-mappings";
    public static final String TEST_PLAN_FILENAME = "execution-context";
    public static final String RESULT_FILENAME = "execution-result";
    public static final String RESULT_FILEPATH = "target//test-automation-results//";

    @JsonProperty("driverType")
    private WebDriverManager.DriverType driverType;
    @JsonProperty("dataFormat")
    private ObjectSerializer.DataFormat dataFormat;
    @JsonProperty("hostAddress")
    private String hostAddress;
    @JsonProperty("retryInterval")
    private int retryInterval;
    @JsonProperty("waitTimeout")
    private int waitTimeout;
    @JsonProperty("retryCount")
    private int retryCount;

    @JsonProperty("credentialList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final HashMap<String, CredentialItem> credentialList;
    @JsonProperty("pageAddressList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final HashMap<String, String> pageAddressList;
    @JsonProperty("dataSourceList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final HashMap<String, String> dataSourceList;

    public ExecutionSettings() {
        this.credentialList = new HashMap<>();
        this.pageAddressList = new HashMap<>();
        this.dataSourceList = new HashMap<>();
        this.hostAddress = "github.com";
        this.driverType = WebDriverManager.DriverType.CHROME;
        this.dataFormat = ObjectSerializer.DataFormat.JSON;
        this.retryCount = 3;
        this.waitTimeout = 30;
        this.retryInterval = 500;

        this.credentialList.put("default.user", new CredentialItem("Some User", "defaultuser", "somepassword"));
        this.pageAddressList.put("some.page", "path/to/page/route");
        this.dataSourceList.put("some.file", "path/to/file/location.txt");
    }

    public int getRetryCount() { return this.retryCount; }

    public int getWaitTimeout() { return this.waitTimeout; }

    public int getRetryInterval() { return this.retryInterval; }

    public WebDriverManager.DriverType getDriverType() { return this.driverType; }

    public ObjectSerializer.DataFormat getDataFormat() { return this.dataFormat; }

    public String getDatasource(String key) { return this.dataSourceList.get(key); }

    public CredentialItem getCredential(String key) { return this.credentialList.get(key); }

    public String getPageAddress(String pageKey, String credentialKey) {
        if (!this.hostAddress.contains("@") && credentialKey != null) {
            CredentialItem credential = this.getCredential(credentialKey);
            if (credential == null)
                throw new NullPointerException(String.format("CREDENTIAL '%s' NOT FOUND.", credentialKey));
            this.hostAddress = String.format(
                    "%s:%s@%s",
                    credential.getUsername(),
                    credential.getPassword(),
                    this.hostAddress);
        }

        if (!this.hostAddress.contains("https://"))
            this.hostAddress = String.format("https://%s", this.hostAddress);

        if (pageKey != null && pageKey.length() > 0)
            return String.format("%s/%s", this.hostAddress, this.pageAddressList.get(pageKey));

        return this.hostAddress;
    }
}