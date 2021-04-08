package com.xifeng.util.craw;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.xifeng.util.io.FileUtil;

public class Doc88Crawler {
	
	final static String DEV_OPS_URL = "http://www.doc88.com/p-9119146851325.html";
	static WebDriver webDriver;
	
	static void init() {
		
		String chromeDriverPath = "C:\\Users\\xiezb\\Downloads\\chromedriver_80.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions chromeOptions = new ChromeOptions();
		webDriver = new ChromeDriver(chromeOptions);
	}
	
	static void viewPage(String url) {
		if(webDriver == null) {
			System.out.println("webDriver need init first...");
			return;
		}
		webDriver.get(url);
		webDriver.manage().window().setSize(new Dimension(1170,840));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement closeIcon = webDriver.findElement(By.className("edit-tips-closed"));
		closeIcon.click();
		
//		WebElement bottomLineElement = webDriver.findElement(By.className("copyrught"));
		int n = 1;
		JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
		TakesScreenshot takesScreenshot = (TakesScreenshot)webDriver;
		jsExecutor.executeScript("window.scrollTo(0,"+58+")");
		String path = "F:\\xiezb\\svn\\temp\\";
//		while(n<200) {
//			File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//			FileUtil.copyFile(screenShotFile,path + n +".png");
//			jsExecutor.executeScript("window.scrollTo(0,"+400*n+")");
//			n++;
//			if(n ==2) {
//				WebElement conituneButtonElement = webDriver.findElement(By.id("continueButton"));
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				conituneButtonElement.click();
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			System.out.println(300*n);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public static void main(String[] args) {
		Doc88Crawler.init();
		Doc88Crawler.viewPage(DEV_OPS_URL);
//		webDriver.quit();
	}

}
