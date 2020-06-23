package com.yu.test.log;

import com.yu.log.Log;
import com.yu.log.LogFactory;
import com.yu.log.StaticLog;
import com.yu.log.dialect.commons.ApacheCommonsLogFactory;
import com.yu.log.dialect.console.ConsoleLogFactory;
import com.yu.log.dialect.slf4j.Slf4jLogFactory;
import com.yu.log.dialect.tinylog.TinyLogFactory;
import com.yu.log.level.Level;
import org.junit.Ignore;
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

    /**
     * 兼容slf4j日志消息格式测试，即第二个参数是异常对象时正常输出异常信息
     */
    @Test
//    @Ignore
    public void logWithExceptionTest() {
        Log log = LogFactory.get();
        Exception e = new Exception("test Exception");
        log.error("我是错误消息", e);
    }
    private static final String LINE = "----------------------------------------------------------------------";

    @Test
    public void consoleLogTest(){
        LogFactory factory = new ConsoleLogFactory();
        LogFactory.setCurrentLogFactory(factory);
        Log log = LogFactory.get();

        log.info(null);
        log.info("This is custom '{}' log\n{}", factory.getName(), LINE);
    }
    @Test
    public void commonsLogTest(){
        LogFactory factory = new ApacheCommonsLogFactory();
        LogFactory.setCurrentLogFactory(factory);
        Log log = LogFactory.get();

        log.info(null);
        log.info("This is custom '{}' log\n{}", factory.getName(), LINE);
    }
    @Test
    public void tinyLogTest(){
        LogFactory factory = new TinyLogFactory();
        LogFactory.setCurrentLogFactory(factory);
        Log log = LogFactory.get();

        log.info(null);
        log.info("This is custom '{}' log\n{}", factory.getName(), LINE);
    }

    @Test
    public void slf4jTest(){
        LogFactory factory = new Slf4jLogFactory(false);
        LogFactory.setCurrentLogFactory(factory);
        Log log = LogFactory.get();

        log.info(null);
        log.info("This is custom '{}' log\n{}", factory.getName(), LINE);
    }
}
