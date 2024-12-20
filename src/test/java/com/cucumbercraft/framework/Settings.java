package com.cucumbercraft.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton class that encapsulates the user settings specified in the
 * properties file of the framework
 *
 * @author Cognizant
 */
public class Settings {
    private static final Properties properties = loadFromPropertiesFile();

    static Logger log = LogManager.getLogger(Settings.class);

    private Settings() {
        // To prevent external instantiation of this class
    }

    /**
     * Function to return the singleton instance of the {@link Properties}
     * object
     *
     * @return Instance of the {@link Properties} object
     */
    public static Properties getInstance() {
        return properties;
    }

    private static Properties loadFromPropertiesFile() {
        Properties properties = new Properties();
        String relativePath = new File(System.getProperty("user.dir"))
                .getAbsolutePath();
        relativePath = relativePath + Util.getFileSeparator() + "src"
                + Util.getFileSeparator() + "test" + Util.getFileSeparator()
                + "resources";

        try {
            properties.load(new FileInputStream(relativePath
                    + Util.getFileSeparator() + "Global Settings.properties"));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}