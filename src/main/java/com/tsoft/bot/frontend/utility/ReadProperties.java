package com.tsoft.bot.frontend.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private final static String PATH_PROPERTIES = "/src/main/resources/";
    private final static String EXTENSION = ".properties";

    public static String get(String name, String file){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(FileHelper.getProjectFolder()+PATH_PROPERTIES+file+EXTENSION));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }
}