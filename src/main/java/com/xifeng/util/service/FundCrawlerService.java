package com.xifeng.util.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xifeng.util.craw.FundInfo;
import com.xifeng.util.craw.FundStockVo;
import com.xifeng.util.dao.FundDao;
import com.xifeng.util.dao.model.FundDetail;
import com.xifeng.util.dao.model.FundStock;

@Service
public class FundCrawlerService {
	
	@Resource
	FundDao fundDao;
	
	@Transactional
	public void saveCrawlerData(FundInfo fundInfo) {
		if(fundInfo.isClosed()) {
			fundDao.markClosed(fundInfo.getFundNo());
			System.out.println("markClosed fundInfo  " + fundInfo.getFundNo() + " success");
			return;
		}
		FundDetail fundDetail = new FundDetail();
		BeanUtils.copyProperties(fundInfo, fundDetail);
		fundDao.saveFundDetail(fundDetail);
		fundDao.markExecuted(fundInfo.getFundNo());
		
		System.out.println("save fundInfo  " + fundInfo.getFundNo() + " success");
		List<FundStockVo> stockVoList = fundInfo.getStockList();
		if(CollectionUtils.isEmpty(stockVoList)) {
			return;
		}
		FundStock stock;
		List<FundStock> stockList = new ArrayList<>();
		for(FundStockVo vo : stockVoList) {
			stock = new FundStock();
			BeanUtils.copyProperties(vo, stock);
			stock.setFundNo(fundDetail.getFundNo());
			stockList.add(stock);
		}
		
		fundDao.saveBatchFundStock(stockList);
		System.out.println("save stockList  " + fundInfo.getFundNo() + " success");
	}

}
