package main.java.com.framework.logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class LoggingManager {
    private final LogManager logManager;
    private final Logger logger;

    private static LoggingManager current;

    public LoggingManager(String loggerName) {
        this.logManager = LogManager.getLogManager();
        this.logger = Logger.getLogger(loggerName);

        try {
            this.logManager.readConfiguration(new FileInputStream("./logging.config"));
        } catch (IOException e) {
            this.logger.log(Level.SEVERE, "Error in loading configuration",e);
        }
    }

    public static synchronized LoggingManager getCurrent() {
        return LoggingManager.getCurrent(UUID.randomUUID().toString());
    }

    public static synchronized LoggingManager getCurrent(String loggerName) {
        if (LoggingManager.current == null) {
            LoggingManager.current = new LoggingManager(loggerName);
        }

        return LoggingManager.current;
    }

    public static Logger getLogger() {
        return LoggingManager.getCurrent().logger;
    }
}
