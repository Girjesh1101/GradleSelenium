package orangehrm.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverConfig {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public  static WebDriver  getDriver(){

       if(driver == null){
           String browser = System.getProperty("browser", "chrome");
           switch (browser.toLowerCase()){
               case "firefox" :
                   WebDriverManager.firefoxdriver().setup();
                   driver = new FirefoxDriver();
                    break;
               case "edge" :
                   WebDriverManager.edgedriver().setup();
                   driver = new EdgeDriver();
                   break;
               case "remote" :
//                   set up code
//                   driver = new RemoteWebDriver(url);
                   break;
               default:

                   WebDriverManager.chromedriver().setup();
                   driver = new ChromeDriver();
           }

           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       }

       return driver;
    }

    public  static WebDriverWait  getWait(){

        if(wait == null){
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait;
    }

    public  static void  quitDriver(){

        if(driver != null){
            driver.quit();
            driver = null;
            wait = null;
        }
    }

}
