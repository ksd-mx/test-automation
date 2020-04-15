package main.framework.logging;

import main.framework.services.DefaultLocalService;

import java.util.logging.Logger;

public final class LoggingManager extends DefaultLocalService {
    public static final String SERVICE_KEY = "logging.manger";

    private final Logger logger;

    public LoggingManager(String contextId) {
        super(contextId);

        this.logger = Logger.getLogger(contextId);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void close() {

    }
}
