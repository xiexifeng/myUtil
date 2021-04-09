package com.xifeng.util.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xifeng.util.UitlApplication;
import com.xifeng.util.craw.FoundEastmoneyCrawler;
import com.xifeng.util.craw.FundInfo;
import com.xifeng.util.dao.FundDao;
import com.xifeng.util.dao.model.FundDetail;
import com.xifeng.util.dao.model.FundStock;
import com.xifeng.util.service.FundCrawlerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =UitlApplication.class)
public class FundDaoTests {
	@Resource
	FundDao fundDao;
	@Resource
	FundCrawlerService fundCrawlerService;
	
	@Test
	public void test() {
		System.out.println(JSON.toJSON(fundDao.queryUnmarkWithPage(0)));
	}
	
	@Test
	public void testSaveFundDetail() {
		FundDetail fundInfo = new FundDetail();
		fundInfo.setFundNo("003096");
		
		fundInfo.setNetValueOfUnitTime("./dt[1]//p");
		fundInfo.setNetValueOfUnit("./dd[1]//span[1]");
		fundInfo.setLastDayIncrease("./dd[1]//span[2]");
		fundInfo.setThreeMonthIncrease("./dd[2]//span[2]");
		fundInfo.setThreeYearIncrease("./dd[3]//span[2]");
		
		fundInfo.setLastMonthIncrease("./dd[2]//span[2]");
		fundInfo.setLastYearIncrease("./dd[3]//span[2]");
		
		fundInfo.setCumulativeNetValue("./dd[1]//span[1]");
		fundInfo.setSixMonthIncrease("./dd[2]//span[2]");
		fundInfo.setTotalIncrease("./dd[3]//span[2]");
		
		fundInfo.setTotalMoney("./tr[1]//td[2]");
		fundInfo.setManager("./tr[1]//td[3]");
		fundInfo.setOpenDate("./tr[2]//td[1]");
		fundInfo.setManageCompany("./tr[2]//td[2]");
		fundInfo.setStockLastTime("持仓截止日期: 2020-12-31");
		fundDao.saveFundDetail(fundInfo);
	}
	
	@Test
	public void testSaveFundStock() {
		FundStock fundStock = new FundStock();
		fundStock.setFundNo("003096");
		fundStock.setDailyRiseAndFall("3%");
		fundStock.setPositionRatio("4%");
		fundStock.setStockCode("dddd");
		fundStock.setStockName("dddd");
		
		fundDao.saveFundStock(fundStock);
	}
	
	@Test
	public void testSaveBatchFundStock() {
		List<FundStock> stockList = new ArrayList<>();
		FundStock fundStock = new FundStock();
		fundStock.setFundNo("003092");
		fundStock.setDailyRiseAndFall("3%");
		fundStock.setPositionRatio("4%");
		fundStock.setStockCode("dddd");
		fundStock.setStockName("dddd");
		stockList.add(fundStock);
		
		fundStock = new FundStock();
		fundStock.setFundNo("003092");
		fundStock.setDailyRiseAndFall("3%");
		fundStock.setPositionRatio("4%");
		fundStock.setStockCode("dddd");
		fundStock.setStockName("dddd");
		stockList.add(fundStock);
		
		fundDao.saveBatchFundStock(stockList);
	}
	@Test
	public void testCrawlerSingle() {
		String fundNo = "161725";
		String urlTemplate = "http://fund.eastmoney.com/%s.html";
		String detailPageUrl = String.format(urlTemplate, fundNo);
		FoundEastmoneyCrawler.init();
		FundInfo fundInfo = FoundEastmoneyCrawler.viewFundDetailPage(detailPageUrl, fundNo);
		if(fundInfo == null) {
			return;
		}
		new Thread() {
			public void run() {
				fundCrawlerService.saveCrawlerData(fundInfo);
			}
		}.start();
		FoundEastmoneyCrawler.exit();
	}
	
	
	@Test
	public void testCrawlerBatch() {
		FoundEastmoneyCrawler.init();
		String urlTemplate = "http://fund.eastmoney.com/%s.html";
		while(true) {
			List<String> fundList = fundDao.queryUnmarkWithPage(0);
			if(CollectionUtils.isEmpty(fundList)) {
				break;
			}
			for(String fundNo : fundList) {
				String detailPageUrl = String.format(urlTemplate, fundNo);
				FundInfo fundInfo = FoundEastmoneyCrawler.viewFundDetailPage(detailPageUrl, fundNo);
				if(fundInfo == null) {
					continue;
				}
				fundCrawlerService.saveCrawlerData(fundInfo);
					
			}
		}
		FoundEastmoneyCrawler.exit();
		
	}
}
