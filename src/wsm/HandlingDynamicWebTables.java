package wsm;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

public class HandlingDynamicWebTables {

	public static void main(String[] args) throws ParseException {
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
        
        //Go to manage users page
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar-nav li:nth-child(6)")));
        Actions actions = new Actions(driver);
        WebElement manageUsersElement = driver.findElement(By.cssSelector(".sidebar-nav li:nth-child(6)"));
        actions.moveToElement(manageUsersElement).perform();
        manageUsersElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar-nav li:nth-child(6) li:first-child")));
        WebElement usersElement = driver.findElement(By.cssSelector(".sidebar-nav li:nth-child(6) li:first-child"));
        usersElement.click();
        
        //Fetch number of rows and columns from Dynamic WebTable
        //No.of Columns
        List<WebElement> cols = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/thead/tr/th"));
        System.out.println("No of cols are: " + cols.size());
        
        //No.of Rows
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr/td[1]"));
        System.out.println("No of cols are: " + rows.size());
        
        
        //Fetch cell value of a particular row and column of the Dynamic Table
        WebElement tableUsers = driver.findElement(By.tagName("table"));
        
        //To find third row of table
        WebElement tableRow = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[3]"));
        WebElement tableRowText = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[3]/td[1]"));
        String rowText = tableRow.getText();
        System.out.println("Third row of table: " + rowText);
        
        //TO get 3rd row's 2nd column data
        WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[3]/td[1]"));
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is:" + valueIneed);
        
        //Get lasted date of all the Values in a Column of Dynamic Table
		Date lastedDate = null, startProbationDate;
		String value;
		
		
		// No. of Columns
		List tableCols = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/thead/tr/th"));
		System.out.println("Total No of columns are : " + tableCols.size());
		// No.of rows
		List tableRows = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr/td[1]"));
		System.out.println("Total No of rows are : " + tableRows.size());
		for (int i = 1; i < rows.size(); i++) {
			 value = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div[2]/div/div/table/tbody/tr[" + (i + 1) + "]/td[6]")).getText();	
			if (value.equals(" ")){
		        break;
		    }
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        startProbationDate = formatter.parse(value);
			if(lastedDate == null) {
				lastedDate = startProbationDate;
				break;
			}	
			
			if (startProbationDate.compareTo(lastedDate) > 0) {
				lastedDate = startProbationDate;
			}
		}
		System.out.println("Maximum current price is : " + lastedDate);        
	}
}
