package com.utils;

import org.slf4j.Logger;

public class LoggerUtils {

    private LoggerUtils(){}

    public static void log_info(Logger log, String info){
        log.info(info);
    }

    public static void log_warn(Logger log, String warn){
        log.warn(warn);
    }

    public static void log_error(Logger log, String error){
        log.error(error);
    }

    public static void log_debug(Logger log, String debug){
        log.debug(debug);
    }

}
