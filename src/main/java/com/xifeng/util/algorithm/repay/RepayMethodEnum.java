/**
 * Project Name:MyUtil <br>
 * File Name:RepayMethodEnum.java <br>
 * Package Name:com.xifeng.util.algorithm.repay <br>
 * @author xiezbmf
 * Date:2018年5月29日下午4:54:02 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.algorithm.repay;
/**
 * ClassName: RepayMethodEnum <br>
 * Description: 还款方式枚举
 * @author xiezbmf
 * @Date 2018年5月29日下午4:54:02 <br>
 * @version
 * @since JDK 1.8
 */
public enum RepayMethodEnum {

	FILP("先息后本");
	String name;
	private RepayMethodEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

	