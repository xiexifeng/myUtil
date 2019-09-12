package com.xifeng.util.sina.base;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 下午2:33:34
 */
public class TransCode {
	private String path;

    private String code;
    
    private String method;
    
    private String desc;
    
    private boolean pojo;
    
    public TransCode() {}
    
    public TransCode(String path, String code, String method, String desc){
    	this.path = path;
    	this.method = method;
        this.code = code;
        this.desc = desc;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMethod() {
		return method.toUpperCase();
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isPojo() {
		return pojo;
	}

	public void setPojo(boolean pojo) {
		this.pojo = pojo;
	}

	@Override
	public String toString() {
		return "TransCode [path=" + path + ", code=" + code + ", method=" + method + ", desc=" + desc + ", pojo=" + pojo
				+ "]";
	}
    
    
}
