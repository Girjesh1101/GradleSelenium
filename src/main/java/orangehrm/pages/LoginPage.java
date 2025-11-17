package orangehrm.pages;

import orangehrm.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    protected WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private  WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private  WebElement submitBtn;

    @FindBy(css = "header h6")
    private  WebElement dashboardLoaded;

    @FindBy(className = "oxd-alert-content-text")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  void navigateToLoginPage(String url){
        driver.get(url);
    }

    public  void enterUsername(String username){
        WebDriverConfig.getWait().until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickLogin(){
        submitBtn.click();
    }

    public LoginPage login(String username , String password){

        enterUsername(username);
        enterPassword(password);
        clickLogin();

        return new LoginPage(driver);
    }

    public  boolean isLoadingPageLoaded(){

        return usernameInput.isDisplayed() && passwordInput.isDisplayed();
    }

    public  boolean isDashboardLoaded(){

        WebDriverConfig.getWait().until(ExpectedConditions.visibilityOf(dashboardLoaded));
        System.out.println("Dashboard : "+dashboardLoaded.getText());
        return  dashboardLoaded.isDisplayed();
    }

    public  String getErrorMessage(){

        WebDriverConfig.getWait().until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

}
