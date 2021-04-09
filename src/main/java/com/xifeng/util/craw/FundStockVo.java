package com.xifeng.util.craw;

public class FundStockVo {
	
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
