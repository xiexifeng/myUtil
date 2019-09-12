package com.xifeng.util.sina.base;
/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 下午2:35:27
 */
public class Parameter {
	
	String attr;
	String attrType;
	boolean empty;
	String desc;
	
	public Parameter() {}
	
	public Parameter(String attr,String attrType,boolean empty,String desc) {
		this.attr = attr;
		this.attrType = attrType;
		this.empty = empty;
		this.desc = desc;
	}
	
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return "Parameter [attr=" + attr + ", attrType=" + attrType + ", empty=" + empty + ", desc=" + desc + "]";
	}
	
	

}
