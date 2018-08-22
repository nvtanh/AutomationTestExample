package chapter15;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

public class ImplicitWait {
	public static void main(String[] args) {
		// declaration and instantiation of objects/variables
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");

		// create a firefox options
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/xml,text/plain,text/xml, application/xls, image/jpeg");
		profile.setPreference("browser.download.dir", "/home/nguyen.van.tran.anhb/Image_Automation");
		options.setProfile(profile);

		// Declare a FirefoxDriver with options above
		WebDriver driver = new FirefoxDriver(options);

		// comment the above 2 lines and uncomment below 2 lines to use Chrome
		// System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();

		String baseUrl = "https://edev.framgia.vn";
		String expectedTitle = "";
		String actualTitle = "Biểu mẫu bao gồm 2 lỗi.";

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// login as admin
		WebElement btnLogin = driver.findElement(By.className("btn-login"));
//      WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();

		WebElement txt_email = driver.findElement(By.id("user_email"));
		txt_email.sendKeys("admin.wsm@framgia.com.edev");
		WebElement txtPass = driver.findElement(By.id("user_password"));
		txtPass.sendKeys("123456");
		WebElement btn_signin = driver.findElement(By.className("login-success"));
		btn_signin.click();

		// go to create inform page
		Actions action = new Actions(driver);
		WebElement menuUser = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div/ul/li[6]"));

		action.moveToElement(menuUser).build().perform();

		menuUser.click();

//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[1]/div/div/ul/li[6]/ul/li[1]/a"))); 
//
//		WebElement menuOption = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div/ul/li[6]/ul/li[1]/a"));
//		menuOption.click();
//		
//		// Get link to download
//		WebElement btnDownload = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[1]/div/div/div/a[2]"));
//		btnDownload.click();

//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*
		 * compare the actual title of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test Passed!");
		} else {
			System.out.println("Test Failed");
		}
		// close Fire fox
		driver.close();
	}
}
