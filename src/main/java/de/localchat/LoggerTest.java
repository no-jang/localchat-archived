package de.localchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggerTest.class);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
