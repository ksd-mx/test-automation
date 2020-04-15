package main.framework.services;

import java.util.UUID;

public abstract class DefaultLocalService implements ILocalService, AutoCloseable {
    private final String contextId;

    public DefaultLocalService(String contextId) {
        this.contextId = contextId;
    }

    public String getContextId() {
        return this.contextId;
    }

    public void initialize() {
        System.out.println(String.format("INITIALIZING LOCAL SERVICE: %s", this.getClass().getName()));
    }

    public void close() {
        System.out.println(String.format("TERMINATING LOCAL SERVICE: %s", this.getClass().getName()));
    }
}