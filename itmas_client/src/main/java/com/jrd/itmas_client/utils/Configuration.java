package com.jrd.itmas_client.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jakub on 05.06.16.
 */
public class Configuration {

    public static final String configFileName = "/.itmas";

    public static class Keys {
        public static final String SERVER = "server";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
    }

    private Properties properties;

    public Configuration() throws IOException {
        this("");
    }

    public Configuration(String configurationFilePath) throws IOException {
        if (configurationFilePath.isEmpty()) {
            configurationFilePath = getConfigurationFilePath();
        }

        properties = new Properties();
        String propFileName = configurationFilePath;
        InputStream inputStream = new FileInputStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private String getConfigurationFilePath() {
        String homeDir = System.getProperty("user.home");
        return homeDir + configFileName;
    }
}