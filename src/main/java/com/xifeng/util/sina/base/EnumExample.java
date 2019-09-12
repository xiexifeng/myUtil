package com.xifeng.util.sina.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月13日 下午5:38:01
 */
public enum EnumExample {

	EXAMPLE("1","测试","测试用例");
	EnumExample(String code,String codeDesc,String desc){
		this.code = code;
		this.codeDesc = codeDesc;
		this.desc = desc;
	}
	private static final Map<String, EnumExample> mappings = new HashMap<>(8);
	static {
		for (EnumExample value : values()) {
			mappings.put(value.name(), value);
		}
	}
	public static EnumExample resolve(String method) {
		return (method != null ? mappings.get(method) : null);
	}


	public boolean matches(String method) {
		return (this == resolve(method));
	}
	
	/**
	 * 对应的code
	 */
	private String code;
	/**
	 * 对应code的描述
	 */
	private String codeDesc;
	/**
	 * 补充描述
	 */
	private String desc;
	
	public String getCode() {
		return code;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public String getDesc() {
		return desc;
	}
	
	
}
