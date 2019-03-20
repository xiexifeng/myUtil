/**
 * Project Name:MyUtil <br>
 * File Name:FirstInterestLastPrincipalRepay.java <br>
 * Package Name:com.xifeng.util.algorithm.repay.builder <br>
 * @author xiezbmf
 * Date:2018年5月29日下午4:58:20 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.algorithm.repay.builder;

import com.xifeng.util.algorithm.repay.RepayMethodEnum;
import com.xifeng.util.algorithm.repay.RepayPlan;
import com.xifeng.util.algorithm.repay.RepayPlanBuilder;

/**
 * ClassName: FirstInterestLastPrincipalRepay <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月29日下午4:58:20 <br>
 * @version
 * @since JDK 1.8
 */
public class FirstInterestLastPrincipalRepay extends RepayPlanBuilder {

	@Override
	protected RepayPlan build(RepayPlan repayPlan) {
		
		return null;
	}

	@Override
	public RepayMethodEnum getRepayMethod() {
		return RepayMethodEnum.FILP;
	}

}

	