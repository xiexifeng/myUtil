package com.xifeng.util.dao.model;

public class FundStock {
	Long id;
	
	/**
	 * 基金编号
	 */
	String fundNo;
	/**
	 * 股票代码
	 */
	String stockCode;
	/**
	 * 股票名称
	 */
	String stockName;
	/**
	 * 持仓比例
	 */
	String positionRatio;
	/**
	 * 当日涨跌幅 
	 */
	String dailyRiseAndFall;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFundNo() {
		return fundNo;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getPositionRatio() {
		return positionRatio;
	}
	public void setPositionRatio(String positionRatio) {
		this.positionRatio = positionRatio;
	}
	public String getDailyRiseAndFall() {
		return dailyRiseAndFall;
	}
	public void setDailyRiseAndFall(String dailyRiseAndFall) {
		this.dailyRiseAndFall = dailyRiseAndFall;
	}
}
