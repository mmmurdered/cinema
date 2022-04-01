package com.murdered.cinema.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MappingProperties {
    private static final Logger logger = Logger.getLogger(MappingProperties.class);

    private static final String propertyFile = "pages.properties";
    private static MappingProperties instance;
    private final Properties properties;

    private MappingProperties() {
        logger.info("Initializing mapping properties class");
        properties = new Properties();
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(propertyFile);
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MappingProperties getInstance() {
        if (instance == null) {
            instance = new MappingProperties();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
