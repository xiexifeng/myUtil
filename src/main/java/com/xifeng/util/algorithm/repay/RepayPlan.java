/**
 * Project Name:MyUtil <br>
 * File Name:RepayPlan.java <br>
 * Package Name:com.xifeng.util.algorithm.repay <br>
 * @author xiezbmf
 * Date:2018年5月29日下午4:54:29 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.algorithm.repay;

import java.util.List;

/**
 * ClassName: RepayPlan <br>
 * Description: 还款计划
 * 
 * @author xiezbmf
 * @Date 2018年5月29日上午10:38:47 <br>
 * @version
 * @since JDK 1.8
 */
public class RepayPlan {
	/**
	 * loanAmt:借款金额.
	 */
	double loanAmt;
	/**
	 * periods:借款期数.
	 */
	int periods;
	/**
	 * loanAnnualRate:借款年利率.
	 */
	double loanAnnualRate;
	/**
	 * consultFeeAnnualRate:咨询费年利率.
	 */
	double consultFeeAnnualRate;
	/**
	 * serviceFeeAnnualRate:服务费年利率.
	 */
	double serviceFeeAnnualRate;
	
	List<RepayPlanDetail> repayDetailList;
	
	RepayMethodEnum repayMethod;
	
	public RepayPlan() {}
	
	public RepayPlan(double loanAmt,int periods,double loanAnnualRate,RepayMethodEnum repayMethod) {
		this.loanAmt = loanAmt;
		this.periods = periods;
		this.loanAnnualRate = loanAnnualRate;
		this.repayMethod = repayMethod;
	}
	
	public RepayPlan(double loanAmt,int periods,double loanAnnualRate,RepayMethodEnum repayMethod,double consultFeeAnnualRate,double serviceFeeAnnualRate) {
		this(loanAmt,periods,loanAnnualRate,repayMethod);
		this.consultFeeAnnualRate = consultFeeAnnualRate;
		this.serviceFeeAnnualRate = serviceFeeAnnualRate;
	}

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public int getPeriods() {
		return periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}

	public double getLoanAnnualRate() {
		return loanAnnualRate;
	}

	public void setLoanAnnualRate(double loanAnnualRate) {
		this.loanAnnualRate = loanAnnualRate;
	}

	public double getConsultFeeAnnualRate() {
		return consultFeeAnnualRate;
	}

	public void setConsultFeeAnnualRate(double consultFeeAnnualRate) {
		this.consultFeeAnnualRate = consultFeeAnnualRate;
	}

	public double getServiceFeeAnnualRate() {
		return serviceFeeAnnualRate;
	}

	public void setServiceFeeAnnualRate(double serviceFeeAnnualRate) {
		this.serviceFeeAnnualRate = serviceFeeAnnualRate;
	}

	public List<RepayPlanDetail> getRepayDetailList() {
		return repayDetailList;
	}

	public void setRepayDetailList(List<RepayPlanDetail> repayDetailList) {
		this.repayDetailList = repayDetailList;
	}

	public RepayMethodEnum getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(RepayMethodEnum repayMethod) {
		this.repayMethod = repayMethod;
	}
	
	
}

	