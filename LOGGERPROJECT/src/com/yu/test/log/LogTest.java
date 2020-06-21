package com.yu.test.log;

import com.yu.log.Log;
import com.yu.log.LogFactory;
import com.yu.log.StaticLog;
import com.yu.log.level.Level;
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
    @Test
    public void logTest(){
        Log log = LogFactory.get();

        // 自动选择日志实现
        log.debug("This is {} log", Level.DEBUG);
        log.info("This is {} log", Level.INFO);
        log.warn("This is {} log", Level.WARN);
        log.error("This is {} log", Level.ERROR);

//		Exception e = new Exception("test Exception");
//		log.error(e, "This is {} log", Level.ERROR);
    }
}
