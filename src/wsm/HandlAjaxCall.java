package wsm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlAjaxCall {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		WebDriver driver = new FirefoxDriver();
 	
        String baseUrl = "https://edev.framgia.vn/";
        String email = "admin.wsm@framgia.com.edev";
        String password = "123456";
        driver.get(baseUrl);
        //Login
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement btnLogin = driver.findElement(By.className("btn-login"));
        btnLogin.click();
        WebElement emailElement = driver.findElement(By.id("user_email"));
        WebElement passwordElement = driver.findElement(By.id("user_password"));
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        WebElement btnSubmitLogin = driver.findElement(By.className("login-success"));
        btnSubmitLogin.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("prev")));
        WebElement prevButton = driver.findElement(By.id("prev"));
        prevButton.click();
        
        Thread.sleep(3000);
        WebElement monthText = driver.findElement(By.xpath("//*[@id=\"label\"]/span"));
        String monthTextValue = monthText.getText().toLowerCase();
        System.out.println(monthTextValue);
        if(monthTextValue.toLowerCase().equals("tháng 7")) {
        	System.out.println("Test pass");
        } else {
        	System.out.println("Test fail");
        }        
	}

}
