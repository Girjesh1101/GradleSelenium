package orangehrm.test;

import orangehrm.BaseTest;
import orangehrm.config.ConfigReader;
import orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage ;

    @BeforeMethod
    public void setUpTest(){
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public  void loginWithValidCred(){

        try {
            loginPage.navigateToLoginPage(ConfigReader.getUrl());
            loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

            Assert.assertTrue(loginPage.isDashboardLoaded() ,"Dashboard page should be Displayed after login");
        } catch (Exception e) {
            throw new RuntimeException("Login Failed "+e.getMessage());
        }
    }

    @Test(priority = 2)
    public void loginWithInvalidCred(){

//        loginPage.navigateToLoginPage(ConfigReader.getUrl());
        loginPage.login("invalidUsername" ,"invalidPassword");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid") ,"Error message should indicate with invalid");
    }


}
