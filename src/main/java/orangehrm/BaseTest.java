package orangehrm;

import orangehrm.config.ConfigReader;
import orangehrm.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver= WebDriverConfig.getDriver();
        driver.get(ConfigReader.getUrl());
    }

    @AfterMethod
    public void quitDriver(){
        WebDriverConfig.quitDriver();
    }
}
