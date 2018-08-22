package chapter17;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavascriptExcute {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		WebDriver driver = new FirefoxDriver();

		driver.get("http://www.anaesthetist.com/mnm/javascript/calc.htm");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Declare and set the start time
		long start_time = System.currentTimeMillis();

		// Call executeAsyncScript() method
		js.executeScript("window.setTimeout(arguments[arguments.length - 1], 4000);");

		// 1-) Click "9"
		driver.findElement(By.name("nine")).click();

		// 2-) Click "+"
		driver.findElement(By.name("add")).click();

		// 3-) Click "3"
		driver.findElement(By.name("three")).click();

		// 4-) Declare JavaScriptExecutor and call "Calculate()" function
		js.executeScript("Calculate();");

		// 5-) Assert that result is 12
		WebElement result = driver.findElement(By.name("Display"));

		// Alert Pop window
		js.executeScript("alert('Are you agree?');");
		driver.switchTo().alert().accept();

		WebElement fiveButton = driver.findElement(By.name("five"));

		// Hide an element
		js.executeScript("arguments[0].style.display='none'", fiveButton);

		// Show an element
		js.executeScript("arguments[0].style.display='block'", fiveButton);

		// Highlight an Element
		js.executeScript("arguments[0].style.border='3px dotted red'", fiveButton);

		// Get the difference (currentTime - startTime) of times.
		System.out.println("Curernt time1: " + System.currentTimeMillis() + "Passed time: "
				+ (System.currentTimeMillis() - start_time));


		// close driver
		// driver.quit();
	}
}
