package org.mycompany.animals.dogs.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;

/**
 * This a configuration class for animals-registry library such as enable logging or setting up default date format
 * for the field dateFormat {@link org.mycompany.animals.dogs.domain.Dog}
 *
 * @author Antonio Fernandez Alhambra
 *
 */
public class DogRegistryConfig {

    /**
     * Log instance used for logging purposes.
     */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DogRegistryConfig.class);

    /**
     * The default date format to be use for parsing {@link org.mycompany.animals.dogs.domain.Dog}
     */
    private String dateFormat = "dd-MM-yyyy";

    /**
     *  <p>Getter method to return dateFormat field</p>
     * @return {@link java.time.format.DateTimeFormatter}
     */
    public DateTimeFormatter getDateFormat() {
        return DateTimeFormatter.ofPattern(dateFormat);
    }

    /**
     * <p>Set method to specify dateFormat field to use</p>
     * @param dateFormat String with the date format to be used and set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Static method to enable default logging to console. Default log level will be DEBUG.
     *
     * @param enableLogging If true, it will enable logging, otherwise it will disable it
     */
    public static void setEnableLogging(boolean enableLogging){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mycompany.animals");
        if (enableLogging) {
            log.debug("logging is enabled to DEBUG level");
            rootLogger.setLevel(Level.DEBUG);
        } else {
            log.debug("logging is disabled");
            rootLogger.setLevel(Level.OFF);
        }
    }
}
