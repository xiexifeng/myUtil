package com.xifeng.util.craw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.alibaba.fastjson.JSON;
import com.xifeng.util.io.FileUtil;

public class FoundEastmoneyCrawler {
	
static WebDriver webDriver;
	
	static void init() {
		
		String chromeDriverPath = "C:\\Users\\xiezb\\Downloads\\chromedriver_80.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions chromeOptions = new ChromeOptions();
		webDriver = new ChromeDriver(chromeOptions);
	}
	
	static void viewPage(String url,String id) {
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
		String html = webDriver.getPageSource();
		String path = "F:\\xiezb\\svn\\temp\\"+id+".html";
		FileUtil.writeFile(path, html);
		FundInfo fundInfo = new FundInfo();
		
		fundInfo.setFundNo(id);
		
		WebElement dataItem02 = webDriver.findElement(By.xpath("//div[@class='dataOfFund']//dl[@class='dataItem02']"));
		
		fundInfo.setNetValueOfUnitTime(dataItem02.findElement(By.xpath("./dt[1]//p")).getText());
		fundInfo.setNetValueOfUnit(dataItem02.findElement(By.xpath("./dd[1]//span[1]")).getText());
		fundInfo.setLastDayIncrease(dataItem02.findElement(By.xpath("./dd[1]//span[2]")).getText());
		fundInfo.setThreeMonthIncrease(dataItem02.findElement(By.xpath("./dd[2]//span[2]")).getText());
		fundInfo.setThreeYearIncrease(dataItem02.findElement(By.xpath("./dd[3]//span[2]")).getText());
		
		WebElement dataItem01 = webDriver.findElement(By.xpath("//div[@class='dataOfFund']//dl[@class='dataItem01']"));
		fundInfo.setLastMonthIncrease(dataItem01.findElement(By.xpath("./dd[2]//span[2]")).getText());
		fundInfo.setLastYearIncrease(dataItem01.findElement(By.xpath("./dd[3]//span[2]")).getText());
		
		WebElement dataItem03 = webDriver.findElement(By.xpath("//div[@class='dataOfFund']//dl[@class='dataItem03']"));
		fundInfo.setCumulativeNetValue(dataItem03.findElement(By.xpath("./dd[1]//span[1]")).getText());
		fundInfo.setSixMonthIncrease(dataItem03.findElement(By.xpath("./dd[2]//span[2]")).getText());
		fundInfo.setTotalIncrease(dataItem03.findElement(By.xpath("./dd[3]//span[2]")).getText());
		
		WebElement infoOfFundTbody = webDriver.findElement(By.xpath("//div[@class='infoOfFund']//table//tbody"));
		fundInfo.setTotalMoney(infoOfFundTbody.findElement(By.xpath("./tr[1]//td[2]")).getText());
		fundInfo.setManager(infoOfFundTbody.findElement(By.xpath("./tr[1]//td[3]")).getText());
		fundInfo.setOpenDate(infoOfFundTbody.findElement(By.xpath("./tr[2]//td[1]")).getText());
		fundInfo.setManageCompany(infoOfFundTbody.findElement(By.xpath("./tr[2]//td[2]")).getText());
		
		List<FundStock> stockList = new ArrayList<>();
		
		List<WebElement> trList = webDriver.findElement(By.xpath("//div[@class='poptableWrap']/table/tbody")).findElements(By.tagName("tr"));
		trList.remove(0);
		for(WebElement tr:trList) {
			FundStock stock = new FundStock();
			stock.setStockName(tr.findElement(By.xpath("./td[1]")).getText());
			stock.setPositionRatio(tr.findElement(By.xpath("./td[2]")).getText());
			stock.setStockCode(tr.findElement(By.xpath("./td[3]")).getAttribute("stockcode"));
			stock.setDailyRiseAndFall(tr.findElement(By.xpath("./td[3]")).getText());
			stockList.add(stock);
		}
		
		
		fundInfo.setStockList(stockList);
		System.out.println(JSON.toJSON(fundInfo));
		
	}
	
	public static void main(String[] args) {
		FoundEastmoneyCrawler.init();
		String id = "161725";
		String urlTemplate = "http://fund.eastmoney.com/%s.html";
		String detailPageUrl = String.format(urlTemplate, id);
		System.out.println(detailPageUrl);
		FoundEastmoneyCrawler.viewPage(detailPageUrl,id);
//		webDriver.quit();
	}

}
