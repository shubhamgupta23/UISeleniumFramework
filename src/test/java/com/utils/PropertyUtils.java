package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {

    private static Properties prop;

    public static void loadProperties(){
        try{
            String env = System.getProperty("environment");
            if(env==null || env.isBlank()){
                throw new IllegalArgumentException("Environment variable is not set. Please set it...");
            }
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/profiles/"+env+".properties");
            prop = new Properties();
            prop.load(fis);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String property){
        return prop.getProperty(property);
    }

}
