package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentRepository;

@SpringBootTest
public class E2E_TestCreateAssignment {

	public static final String CHROME_DRIVER_FILE_LOCATION = 
			"C:/Drivers/selenium/chromedriver.exe";
	public static final String URL = "http://localhost.com:3000";
	public static final int SLEEP_DURATION = 1000;
	public static final int TEST_COURSE_ID = 999001;
	public static final String TEST_COURSE_TITLE = "cst363-database";
	public static final String TEST_DUE_DATE = "2022-11-01";
	public static final String TEST_NAME = "homework 5";
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test
	public void addAssignment() throws Exception {
		//delete if assignment exists
		Assignment x = null;
		do {
			x = assignmentRepository.findByNameAndCourseId(TEST_NAME,TEST_COURSE_ID);
			
			if (x != null) {
				assignmentRepository.delete(x);
			}
			
		} while (x != null);
		
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
			
			we = driver.findElement(By.name("assignmentName"));
			we.sendKeys(TEST_NAME);
			
			we = driver.findElement(By.name("dueDate"));
			we.sendKeys(TEST_DUE_DATE);
			
			we = driver.findElement(By.name("courseTitle"));
			we.sendKeys(TEST_COURSE_TITLE);
			
			we = driver.findElement(By.xpath("//button[@id='Add']"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			Assignment a = assignmentRepository.findByNameAndCourseId(TEST_NAME,TEST_COURSE_ID);
			
			assertNotNull(a,"assignment not found");
			
			Assignment a1 = assignmentRepository.findByNameAndCourseId(TEST_NAME,TEST_COURSE_ID);
			
			if (a1 != null)
				assignmentRepository.delete(a1);

            driver.quit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
			
		} finally {
			driver.quit();
		}
	}
	
}
