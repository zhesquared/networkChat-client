package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static logger.DateTime.getCurrentTime;

public class Settings {

    private static Properties properties;

    static {
        try (FileInputStream file = new FileInputStream("src/main/resources/settings.properties");) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Cannot get access to properties file");
        }
    }

    static {
        try (FileInputStream file = new FileInputStream("src/main/resources/message.properties");) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException exception) {
            System.out.println(getCurrentTime() + " " + "Cannot get access to properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
