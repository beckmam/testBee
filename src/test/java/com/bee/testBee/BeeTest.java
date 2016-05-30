package com.bee.testBee;
/*
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
*/
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BeeTest 

{
	private WebDriver driver = null;
	  
	@BeforeMethod
	public void tearUp() {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.goetui.com");
		driver.manage().window().maximize();
		WebElement link = driver.findElement(By.linkText("登录"));
		link.click();
 	}
	
	@Test(groups = {"tom"})
	public void tomTest() {
		System.out.println("hello,this is tom");
		Assert.assertEquals(1,2);
		driver.close();

	}
  
	@Test(groups = {"andy"})
	public void andyTest() {
		System.out.println("hello,this is andy");
		driver.close();
	}
  
	@Test(groups = {"jim"})
	public void jimTest() {
		System.out.println("hello,this is jim");
		driver.close();
	}
  
	@Test
	public void foundFailed(){
		Assert.assertEquals(1,2);
		driver.close();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
      if (!result.isSuccess())
    	  catchExceptions(result);
	}
	
	@AfterMethod
     public void tearDown(){
		driver.quit();
	 }

	public void catchExceptions(ITestResult result) {
      System.out.println("result" + result+"\n");
      String methodName = result.getName();
      System.out.println(methodName);
      if (!result.isSuccess()) {
          File file = new File("");
          Reporter.setCurrentTestResult(result);
          System.out.println(file.getAbsolutePath()+"\n");
          Reporter.log(file.getAbsolutePath());
          String filePath = file.getAbsolutePath();
          Reporter.log("<img src='"+filePath+"/"+result.getName()+".png' hight='100' width='100'/>");
          File screenShotFile = new File(filePath+"/"+result.getName()+".png");
          try {
        	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	  FileUtils.copyFile(scrFile, screenShotFile);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
	}
}
