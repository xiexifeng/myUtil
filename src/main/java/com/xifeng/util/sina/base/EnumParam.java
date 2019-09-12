package com.xifeng.util.sina.base;
/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月13日 下午5:34:36
 */
public class EnumParam {

	/**
	 * 枚举值
	 */
	String type;
	/**
	 * 对应的code
	 */
	String code;
	/**
	 * 对应code的描述
	 */
	String codeDesc;
	/**
	 * 补充描述
	 */
	String desc;
	
	public EnumParam() {}
	
	public EnumParam(String type,String code,String codeDesc,String desc) {
		this.type = type;
		this.code = code;
		this.codeDesc = codeDesc;
		this.desc = desc;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}
