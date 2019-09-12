package com.xifeng.util.sina.base;

import java.util.Date;

import com.xifeng.util.date.DateUtil;
import com.xifeng.util.sina.Constant;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 下午1:22:35
 */
public class FileDescription {
	
	String description;
	String copyright;
	String company;
	String author;
	String version;
	
	public FileDescription(String description,String copyright,String company,String author,String version) {
		this.description = description;
		this.copyright = copyright;
		this.company = company;
		this.author = author;
		this.version = version;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String genFileDes() {
		StringBuilder fileDes = new StringBuilder(1000);
		fileDes.append("/**").append(Constant.BR);
		fileDes.append(" * @Description : ").append(this.description).append(Constant.BR);
		fileDes.append(" * @Copyright : ").append(this.copyright).append(Constant.BR);
		fileDes.append(" * @Company : ").append(this.company).append(Constant.BR);
		fileDes.append(" * @author : ").append(this.author).append(Constant.BR);
		fileDes.append(" * @version : ").append(this.version).append(Constant.BR);
		fileDes.append(" * @Date : ").append(DateUtil.format(new Date(), DateUtil.YMD_HMS)).append(Constant.BR);
		fileDes.append(" */").append(Constant.BR);
		return fileDes.toString();
	}

}
