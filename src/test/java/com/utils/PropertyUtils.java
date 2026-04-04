package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {

    private static Properties prop;

    public static void loadProperties(){
        String env = System.getProperty("environment");
        if(env==null || env.isBlank()){
            throw new IllegalArgumentException("Environment variable is not set. Please set it...");
        }
        try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/profiles/"+env+".properties")){
            prop = new Properties();
            prop.load(fis);
        }catch(Exception e){
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public static String getProperty(String property){
        if(prop == null){
            throw new IllegalStateException("Properties not loaded. Call loadProperties() first.");
        }
        return prop.getProperty(property);
    }

    public static String getProperty(String property, String defaultvalue){
        if(prop == null){
            throw new IllegalStateException("Properties not loaded. Call loadProperties() first.");
        }
        return prop.getProperty(property,defaultvalue);
    }

}
