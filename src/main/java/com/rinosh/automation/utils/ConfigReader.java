package com.rinosh.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    static{
        try{
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
        }
        catch(Exception e){
            throw new RuntimeException("Error in loading the file: config.properties");
        }
    }
    public static String get(String key){
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isEmpty()){
            return systemValue;
        }
        return properties.getProperty(key);
    }

    public static String getBaseUrl(){
        String env = get("env");
        String envSpecificUrl = properties.getProperty(env+".baseUrl");
        if (envSpecificUrl==null){
            throw new RuntimeException("No base ]URL defined for environment: "+env);
        }
        return envSpecificUrl;
    }
}
