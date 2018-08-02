package wsm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccessingForms {
	public static void main(String[] args) throws InterruptedException {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		WebDriver driver = new FirefoxDriver();
		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
 	
        String baseUrl = "https://edev.framgia.vn/";
        String email = "admin.wsm@framgia.com.edev";
        String password = "123456";
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);
        //close Fire fox
       
        WebElement btnLogin = driver.findElement(By.className("btn-login"));
        btnLogin.click();
        WebElement emailElement = driver.findElement(By.id("user_email"));
        WebElement passwordElement = driver.findElement(By.id("user_password"));
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        //Remember me
        WebElement rememberElement = driver.findElement(By.className("span-remember"));
        WebElement rememberRealElement = driver.findElement(By.id("user_remember_me"));
        rememberElement.click();       
        if(rememberRealElement.isSelected()) {
        	System.out.println("Login with rememberme");
        } else {
        	System.out.println("Login without rememberme");
        }
        
        //clear() method
        //emailElement.clear();
        //passwordElement.clear();
        WebElement btnSubmitLogin = driver.findElement(By.className("login-success"));
        btnSubmitLogin.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar-nav li:nth-child(6)")));
        Actions actions = new Actions(driver);
        WebElement manageUsersElement = driver.findElement(By.cssSelector(".sidebar-nav li:nth-child(6)"));
        actions.moveToElement(manageUsersElement).perform();
        manageUsersElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar-nav li:nth-child(6) li:first-child")));
        WebElement usersElement = driver.findElement(By.cssSelector(".sidebar-nav li:nth-child(6) li:first-child"));
        usersElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[1]/td[11]/div/a[2]")));
        WebElement editUserElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[1]/td[11]/div/a[2]"));
        editUserElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"user_general_info_attributes_gender_male\"]")));
        WebElement genderUserElement = driver.findElement(By.xpath("//*[@id=\"user_general_info_attributes_gender_male\"]"));
        genderUserElement.click();
        
        WebElement staffTypeUserElement = driver.findElement(By.xpath("//*[@id=\"user_staff_type_id\"]"));
        Select staffTypeUserSelect= new Select(staffTypeUserElement) ;
        //staffTypeUserSelect.selectByVisibleText("Part-time");
        staffTypeUserSelect.selectByIndex(2);
        
        WebElement groupUserElement = driver.findElement(By.xpath("//*[@id=\"user_staff_type_id\"]"));
        Select groupUserUserSelect= new Select(staffTypeUserElement) ;
        //groupUserUserSelect.selectByVisibleText("Part-time");
        groupUserUserSelect.selectByIndex(2);   
        
	
	}
}
