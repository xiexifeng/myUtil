package com.xifeng.util.craw;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.xifeng.util.service.FundCrawlerService;

public class CrawlerThread extends Thread{
	static final String URL_TEMPLATE = "http://fund.eastmoney.com/%s.html";
	FundCrawlerService fundCrawlerService;
	FoundEastmoneyCrawler crawler;
	public static List<String> fundList = new Vector<>();
	public static boolean finished = false;
	public static Object LOCK = new Object();
	String threadName;
	CountDownLatch latch;
	static int total=0;
	
	 
    public CrawlerThread(FundCrawlerService fundCrawlerService,FoundEastmoneyCrawler crawler,String threadName,CountDownLatch latch) {
    	super(threadName);
    	this.fundCrawlerService = fundCrawlerService;
    	this.crawler = crawler;
    	this.threadName = threadName;
    	this.latch = latch;
    }
    
    public void run() {
    	while(true) {
			String fundNo = null;
	    	synchronized (LOCK) {
				if(!fundList.isEmpty()) {
					System.out.println(Thread.currentThread().getName() + " fundList is not empty ,size:"+ fundList.size());
					fundNo = fundList.remove(0);
					System.out.println(Thread.currentThread().getName() + " fundList is not empty ,after remove size:"+ fundList.size());
					total++;
				}else {
					fundNo = null;
					if(finished) {
						latch.countDown();
						System.out.println(Thread.currentThread().getName() + " fundList is empty will exit");
						break;
					}
					try {
						System.out.println(Thread.currentThread().getName() + " fundList is empty will wait 1000ms");
						LOCK.notify();
						LOCK.wait();
						System.out.println(Thread.currentThread().getName() + " fundList is empty ,after wait"+ fundList.size());
					} catch (InterruptedException e) {
						LOCK.notify();
						e.printStackTrace();
					}
				}
			}
	    	if(fundNo != null) {
	    		System.out.println(Thread.currentThread().getName() + " will crawling " + fundNo);
		    	String detailPageUrl = String.format(URL_TEMPLATE, fundNo);
				FundInfo fundInfo = crawler.viewFundDetailPage(detailPageUrl, fundNo);
				if(fundInfo == null) {
					return;
				}
				fundCrawlerService.saveCrawlerData(fundInfo);
				System.out.println(Thread.currentThread().getName() + " had crawled " + fundNo +",total:"+total);
	    	}
	    	
    	}
    	
    }
	
}
