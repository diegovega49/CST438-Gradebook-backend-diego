package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class E2E_TestCreateAssignment {

	public static final String CHROME_DRIVER_FILE_LOCATION = 
			"C:/Drivers/selenium/chromedriver.exe";
	public static final String URL = "http://localhost.com:3000";
	public static final int SLEEP_DURATION = 1000;
	
	@Test
	public void addAssignment() throws Exception {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		
		WebDriver driver = new ChromeDriver();
		
		try {
			WebElement we;
			
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			we = driver.findElement(By.xpath("//button[@id='addAssignment']"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			we = driver.findElement(By.xpath("//button[@id='add']"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
			
		} finally {
			driver.quit();
		}
	}
	
}
