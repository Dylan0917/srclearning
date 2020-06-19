package com.yu.test.log;

import com.yu.log.StaticLog;
import org.junit.Test;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/6/18 16:36
 */
public class LogTest {

    @Test
    public void testStaticLog() {
        StaticLog.debug("This is static {} log", "debug");
        StaticLog.info("This is static {} log", "info");
    }
}
