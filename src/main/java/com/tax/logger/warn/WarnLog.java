package com.tax.logger.warn;


import com.tax.logger.debug.DebugLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.lang.invoke.MethodHandles;

/**
 * Class for demonstration {@link Level#WARN}
 */
@Deprecated
public class WarnLog {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public WarnLog() {
        log.info("{} object has been created", this.getClass().getSimpleName());
        log.warn("this class is deprecated, don't use it!");
        log.warn("please, use {} class instead of it", DebugLog.class.getSimpleName());
    }

}
