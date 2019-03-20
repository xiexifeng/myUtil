/**
 * Project Name:MyUtil <br>
 * File Name:RepayPlanBuilder.java <br>
 * Package Name:com.xifeng.util.algorithm.repay <br>
 * @author xiezbmf
 * Date:2018年5月29日下午4:55:18 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.algorithm.repay;

import com.xifeng.util.AssertUtil;

/**
 * ClassName: RepayPlanBuilder <br>
 * Description: 还款计划生产者
 * @author xiezbmf
 * @Date 2018年5月29日上午10:57:45 <br>
 * @version
 * @since JDK 1.8
 */
public abstract class RepayPlanBuilder {

	protected abstract RepayPlan build(RepayPlan repayPlan);
	
	public abstract RepayMethodEnum getRepayMethod();
	
	public void validate(RepayPlan repayPlan) {
		AssertUtil.isTrue(repayPlan.getLoanAmt()>0, "借款金额需大于0");
		AssertUtil.isTrue(repayPlan.getPeriods()>0, "借款期数需大于0");
		AssertUtil.isTrue(repayPlan.getLoanAnnualRate()>0, "借款年利率需大于0");
		AssertUtil.isTrue(repayPlan.getRepayMethod()==this.getRepayMethod(), "还款方式不支持");
		AssertUtil.isTrue(repayPlan.getConsultFeeAnnualRate()>=0, "咨询费年利率需大于等于0");
		AssertUtil.isTrue(repayPlan.getServiceFeeAnnualRate()>=0, "服务费年利率需大于等于0");
	}
	
	public RepayPlan genRepayPlan(RepayPlan repayPlan) {
		this.validate(repayPlan);
		return this.build(repayPlan);
	}
}

	