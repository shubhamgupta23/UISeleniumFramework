package com.factory;

import org.slf4j.Logger;

public class LoggerFactory {

    public static Logger getLogger(Class<?> classes){
        return org.slf4j.LoggerFactory.getLogger(classes);
    }

}
