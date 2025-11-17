package orangehrm.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {

        try(InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties") ){
                    if(input == null)
                        throw new RuntimeException("Unable to find config file");

                    properties.load(input);
        }catch (Exception e){
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    public  static  String getProperty(String key){

        return properties.getProperty(key);
    }

    public  static  String getUrl(){
        return  getProperty("base.url");
    }

    public  static  String getUsername(){
        return  getProperty("username");
    }

    public static String getPassword(){
        return  getProperty("password");
    }
}
