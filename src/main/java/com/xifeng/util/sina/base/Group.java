package com.xifeng.util.sina.base;

import java.util.List;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 下午5:47:11
 */
public class Group {
	
	TransCode transCode;
	List<Parameter> reqParamList;
	List<Parameter> resParamList;
	List<EnumParam> enumParamList;
	public TransCode getTransCode() {
		return transCode;
	}
	public void setTransCode(TransCode transCode) {
		this.transCode = transCode;
	}
	public List<Parameter> getReqParamList() {
		return reqParamList;
	}
	public void setReqParamList(List<Parameter> reqParamList) {
		this.reqParamList = reqParamList;
	}
	public List<Parameter> getResParamList() {
		return resParamList;
	}
	public void setResParamList(List<Parameter> resParamList) {
		this.resParamList = resParamList;
	}
	public List<EnumParam> getEnumParamList() {
		return enumParamList;
	}
	public void setEnumParamList(List<EnumParam> enumParamList) {
		this.enumParamList = enumParamList;
	}

}
