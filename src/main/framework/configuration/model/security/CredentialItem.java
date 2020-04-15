package main.framework.configuration.model.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CredentialItem {
    @JsonProperty("displayName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String displayName;
    @JsonProperty("username")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String username;
    @JsonProperty("password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String password;

    public CredentialItem() {
        this(null, null, null);
    }

    public CredentialItem(String displayname, String username, String password) {
        this.displayName = displayname;
        this.username = username;
        this.password = password;
    }

    public String getDisplayName() { return this.displayName; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
}
