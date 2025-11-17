import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StanAlone {

    @Test
    public  void standAloneCode() throws InterruptedException {

        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String username ="Admin";
        String password ="admin123";
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("Title : "+driver.getTitle());

        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);

        WebElement dashboardLoad = driver.findElement(By.cssSelector("header h6"));

        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(dashboardLoad));

        System.out.println("Dashboard visible : "+dashboardLoad.getText());

        Thread.sleep(3000);

        List<WebElement> sideMenu = driver.findElements(By.xpath("//ul[@class='oxd-main-menu']/li/a/span"));

        sideMenu.stream().filter(webElement -> webElement.getText().equalsIgnoreCase("Admin")).findFirst().ifPresent(WebElement::click);



        Thread.sleep(3000);

        WebElement usernameInput = driver.findElement(By.xpath("//div[@class='oxd-form-row']/div/div/div/div[2]/input"));
        usernameInput.sendKeys(username);




        WebElement dropdownBtn = driver.findElement(By.xpath("(//div[@class='oxd-select-text--after']/i)[1]"));
        dropdownBtn.click();

        List<WebElement> selectUserRole = driver.findElements(By.xpath("//div[@role='option']/span"));

        selectUserRole.stream().filter(ele -> ele.getText().equalsIgnoreCase("Admin"))
                        .findFirst().ifPresent(WebElement::click);

        WebElement statusDropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-text--after']/i)[2]"));
        statusDropdown.click();

        List<WebElement> status = driver.findElements(By.xpath("//div[@role='option']/span"));
        status.stream().filter(ele -> ele.getText().equalsIgnoreCase("Enabled"))
                        .findFirst().ifPresent(WebElement::click);

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(5000);

        List<WebElement>  allUsername = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div/div[2]"));

        if(allUsername.get(0).getText().equalsIgnoreCase("Admin"))
            System.out.println("Found Username");

        driver.close();
    }
}
