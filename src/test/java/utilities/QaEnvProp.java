package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class QaEnvProp {
    //Singleton design pattern -> private constructor + if condition

    private QaEnvProp(){

    }

    private static Properties properties;

    public static void init() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(new FileInputStream("src/test/resources/env.properties"));
            } catch (Exception exception) {
                System.out.println("File not found");
            }
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
