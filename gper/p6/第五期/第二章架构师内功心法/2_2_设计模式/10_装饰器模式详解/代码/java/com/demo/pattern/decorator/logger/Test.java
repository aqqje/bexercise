package com.demo.pattern.decorator.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    private static final Logger jsonLogger = JsonLoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.error("系统错误");
        jsonLogger.error("系统错误");
    }
}
