package com.tax.logger.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.lang.invoke.MethodHandles;

/**
 * Demo class for demonstration {@link Level#ERROR}
 */
public class ErrorLog {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ErrorLog() {
        log.error("Something in file went wrong... {}", "That's life =\\");
    }

}
