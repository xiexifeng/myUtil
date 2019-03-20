/**
 * Project Name:MyUtil <br>
 * File Name:RepayPlanDetail.java <br>
 * Package Name:com.xifeng.util.algorithm.repay <br>
 * @author xiezbmf
 * Date:2018年5月29日下午4:55:55 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.algorithm.repay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ClassName: RepayPlanDetail <br>
 * Description: 还款明细
 * @author xiezbmf
 * @Date 2018年5月29日下午4:55:55 <br>
 * @version
 * @since JDK 1.8
 */
public class RepayPlanDetail {
	/** 期数 */
	private Integer period;
	/** 本金 */
	private BigDecimal principal;
	/** 利息 */
	private BigDecimal interest;
	/** 剩余本金 */
	private BigDecimal remainPrincipal;
	/** 咨询费 */
	private BigDecimal consultFee;
	/** 服务费/管理费 */
	private BigDecimal serviceFee;
	/** 应还款总额 = 本金 + 利息 */
	private BigDecimal totalRepay;
	/** 还款日期 */
	private Date repayDate;
	/** 还款状态 1:未还,2:已还*/
	private String repayStatus;
	
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}
	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}
	public BigDecimal getConsultFee() {
		return consultFee;
	}
	public void setConsultFee(BigDecimal consultFee) {
		this.consultFee = consultFee;
	}
	public BigDecimal getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public String getRepayStatus() {
		return repayStatus;
	}
	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}
	
}

	