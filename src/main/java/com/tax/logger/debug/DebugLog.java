package com.tax.logger.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.lang.invoke.MethodHandles;

/**
 * Demo class for demonstration {@link Level#INFO}
 */
public class DebugLog {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public DebugLog(int var1, int var2) {
        log.debug("Object has been created with var1={}, var2={}", var1, var2);
    }

}
