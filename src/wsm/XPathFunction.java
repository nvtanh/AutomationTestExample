package wsm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XPathFunction {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		WebDriver driver = new FirefoxDriver();
 	
        String baseUrl = "https://edev.framgia.vn/";
        String email = "admin.wsm@framgia.com.edev";
        String password = "123456";
        driver.get(baseUrl);
        //Login
        WebElement btnLogin = driver.findElement(By.className("btn-login"));
        btnLogin.click();
        WebElement emailElement = driver.findElement(By.id("user_email"));
        WebElement passwordElement = driver.findElement(By.id("user_password"));
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        WebElement btnSubmitLogin = driver.findElement(By.className("login-success"));
        btnSubmitLogin.click();
        
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Total of days off')]")));
        // Contains
        WebElement textTotalDayOff = driver.findElement(By.xpath("//b[contains(text(),'Total of days off:')]"));
        if(textTotalDayOff.getText().equals("Total of days off:")) {
        	System.out.println("Contains: Test Pass");
        } else {
        	System.out.println("Contains: Test Fail");
        }
        // Sibling
        WebElement organization_chartsLink = driver.findElement(By.xpath("//a[contains(@href, '/en/dashboard/organization_charts')]/i/following-sibling::span"));
        if(organization_chartsLink.getText().equals("Organization chart")) {
        	System.out.println("Sibling: Test Pass");
        } else {
        	System.out.println("Sibling: Test Fail");
        }
        
        // Ancestor
        WebElement parentOrganization_charts = driver.findElement(By.xpath("//b[contains(text(),'Total of days off:')]/ancestor::div[contains(@class,'status-label')]"));
        if(organization_chartsLink.getText().equals("Organization chart")) {
        	System.out.println("Ancestor: Test Pass");
        } else {
        	System.out.println("Ancestor: Test Fail");
        }
	}	

}
