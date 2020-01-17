package core;

import com.google.errorprone.annotations.concurrent.LazyInit;

import java.io.FileInputStream;
import java.util.Properties;

public class Property {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/global.properties";
    @LazyInit
    private static Properties properties = initProperty();


    public static String getValue(String key){
        return properties.getProperty(key);
    }

    private static Properties initProperty(){
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
        } catch (Exception e){
            throw new YandexTaskException("Не удалось загрузить Properties", e);
        }
        return prop;
    }

}
