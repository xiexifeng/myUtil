package com.xifeng.util.dao.model;

public class FundDetail {
	
	Long id;
	
	/**
	 * 基金编号
	 */
	String fundNo;
	
	/**
	 * 基金规模
	 */
	String totalMoney;
	
	/**
	 * 基金管理人
	 */
	String manager;
	
	/**
	 * 基金管理公司
	 */
	String manageCompany;
	
	/**
	 * 基金成立日期
	 */
	String openDate;

	/**
	 * 净值估算时间
	 */
	String netWorthEstimationTime;
	/**
	 * 净值估算
	 */
	String netWorthEstimationValue;
	/**
	 * 最新单位净值时间
	 */
	String netValueOfUnitTime;
	/**
	 * 最新单位净值
	 */
	String netValueOfUnit;
	
	/**
	 * 最新累计单位净值
	 */
	String cumulativeNetValue;
	
	/**
	 * 近1日涨幅
	 */
	String lastDayIncrease;
	
	/**
	 * 近1月涨幅
	 */
	String lastMonthIncrease;
	
	/**
	 * 近3月涨幅
	 */
	String threeMonthIncrease;
	
	/**
	 * 近6月涨幅
	 */
	String sixMonthIncrease;
	
	/**
	 * 近1年涨幅
	 */
	String lastYearIncrease;
	
	/**
	 * 近3年涨幅
	 */
	String threeYearIncrease;
	
	/**
	 * 成立来涨幅
	 */
	String totalIncrease;
	
	/**
	 * 持仓数据截止时间
	 */
	String stockLastTime;

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

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManageCompany() {
		return manageCompany;
	}

	public void setManageCompany(String manageCompany) {
		this.manageCompany = manageCompany;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getNetWorthEstimationTime() {
		return netWorthEstimationTime;
	}

	public void setNetWorthEstimationTime(String netWorthEstimationTime) {
		this.netWorthEstimationTime = netWorthEstimationTime;
	}

	public String getNetWorthEstimationValue() {
		return netWorthEstimationValue;
	}

	public void setNetWorthEstimationValue(String netWorthEstimationValue) {
		this.netWorthEstimationValue = netWorthEstimationValue;
	}

	public String getNetValueOfUnitTime() {
		return netValueOfUnitTime;
	}

	public void setNetValueOfUnitTime(String netValueOfUnitTime) {
		this.netValueOfUnitTime = netValueOfUnitTime;
	}

	public String getNetValueOfUnit() {
		return netValueOfUnit;
	}

	public void setNetValueOfUnit(String netValueOfUnit) {
		this.netValueOfUnit = netValueOfUnit;
	}

	public String getCumulativeNetValue() {
		return cumulativeNetValue;
	}

	public void setCumulativeNetValue(String cumulativeNetValue) {
		this.cumulativeNetValue = cumulativeNetValue;
	}

	public String getLastDayIncrease() {
		return lastDayIncrease;
	}

	public void setLastDayIncrease(String lastDayIncrease) {
		this.lastDayIncrease = lastDayIncrease;
	}

	public String getLastMonthIncrease() {
		return lastMonthIncrease;
	}

	public void setLastMonthIncrease(String lastMonthIncrease) {
		this.lastMonthIncrease = lastMonthIncrease;
	}

	public String getThreeMonthIncrease() {
		return threeMonthIncrease;
	}

	public void setThreeMonthIncrease(String threeMonthIncrease) {
		this.threeMonthIncrease = threeMonthIncrease;
	}

	public String getSixMonthIncrease() {
		return sixMonthIncrease;
	}

	public void setSixMonthIncrease(String sixMonthIncrease) {
		this.sixMonthIncrease = sixMonthIncrease;
	}

	public String getLastYearIncrease() {
		return lastYearIncrease;
	}

	public void setLastYearIncrease(String lastYearIncrease) {
		this.lastYearIncrease = lastYearIncrease;
	}

	public String getThreeYearIncrease() {
		return threeYearIncrease;
	}

	public void setThreeYearIncrease(String threeYearIncrease) {
		this.threeYearIncrease = threeYearIncrease;
	}

	public String getTotalIncrease() {
		return totalIncrease;
	}

	public void setTotalIncrease(String totalIncrease) {
		this.totalIncrease = totalIncrease;
	}

	public String getStockLastTime() {
		return stockLastTime;
	}

	public void setStockLastTime(String stockLastTime) {
		this.stockLastTime = stockLastTime;
	}

}
