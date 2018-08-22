package chapter9;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFile {
	public static void main(String[] args) {
		// declaration and instantiation of objects/variables
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		WebDriver driver = new FirefoxDriver();
		// comment the above 2 lines and uncomment below 2 lines to use Chrome
		// System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();

		String baseUrl = "http://edev.framgia.vn";
		String expectedTitle = "";
		String actualTitle = "Biểu mẫu bao gồm 2 lỗi.";

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		// login as admin
		WebDriverWait wait = new WebDriverWait(driver, 20);
		// Wait to a element visibility
		WebElement btnLogin = driver.findElement(By.className("btn-login"));
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
		WebElement txt_email = driver.findElement(By.id("user_email"));
		txt_email.sendKeys("admin.wsm@framgia.com.edev");
		WebElement txtPass = driver.findElement(By.id("user_password"));
		txtPass.sendKeys("123456");
		WebElement btn_signin = driver.findElement(By.className("login-success"));
		btn_signin.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// go to create inform page
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div/div/div/a")).click();

		// upload file into input inform file
		WebElement inputFile = driver.findElement(By.id("inform_file"));
		inputFile.sendKeys("/home/nguyen.van.tran.anhb/Pictures/wsm_perform_11.png");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/input[3]")).click();

		// get text errors
		expectedTitle = driver.findElement(By.className("alert-danger")).getText();

		/*
		 * compare the actual title of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test Passed!" + expectedTitle);
		} else {
			System.out.println("Test Failed" + expectedTitle);
		}
		// close Fire fox
		// driver.close();
	}
}
