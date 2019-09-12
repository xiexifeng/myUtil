package com.xifeng.util.sina.gen;

import static com.xifeng.util.sina.Constant.ATTR_RETRACT;
import static com.xifeng.util.sina.Constant.BR;
import static com.xifeng.util.sina.Constant.EQUAL;
import static com.xifeng.util.sina.Constant.METHOD_RETRACT;
import static com.xifeng.util.sina.Constant.SEMICOLON;
import static com.xifeng.util.sina.Constant.BLANK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.xifeng.util.StringUtil;
import com.xifeng.util.sina.base.Parameter;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 上午11:39:33
 */
public class GenFile {
	
	public static void genPath(String filePath) {
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static void writeFile(String javaFileName,String content) {
		File file = new File(javaFileName);
		System.out.println(file.getAbsolutePath());
		try {
			OutputStream os = new FileOutputStream(file);
			os.write(content.getBytes("UTF-8"));
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteDirectory(String path) {
		File file = new File(path);
		if(file.exists()) {
			if(file.isDirectory()) {
				String[] files = file.list();
				if(files != null) {
					for(String subFile :files) {
						deleteDirectory(path+"/"+subFile);
					}
				}
				file.delete();
				System.out.println(file.getAbsolutePath() + " deleted");
			}else {
				file.delete();
				System.out.println(file.getAbsolutePath() + " deleted");
			}
		}
	}
	
	public static String genAttr(Parameter param) {
		StringBuilder attr = new StringBuilder();
		
		attr.append(METHOD_RETRACT).append("/**").append(BR);
		attr.append(METHOD_RETRACT).append("*").append(param.getDesc()).append(BR);
		attr.append(METHOD_RETRACT).append("*/").append(BR);
		if(!param.isEmpty()) {
			attr.append(METHOD_RETRACT).append("@NotBlank(message = \"").append(param.getDesc()).append("不能为空").append("\")").append(BR);
		}
		attr.append(METHOD_RETRACT).append("private").append(BLANK).append(param.getAttrType()).append(BLANK).append(param.getAttr()).append(SEMICOLON).append(BR);
		return attr.toString();
	}
	public static String genSet(Parameter param) {
		String attr = param.getAttr();
		String attrType = param.getAttrType();
		return genSet(attr,attrType);
	}
	
	public static String genSet(String attr,String attrType) {
		StringBuilder attrSet = new StringBuilder();
		attrSet.append(METHOD_RETRACT).append("public void set")
		.append(StringUtil.firstUpperCase(attr)).append("(").append(attrType).append(BLANK).append(attr).append(") {").append(BR);
		attrSet.append(ATTR_RETRACT).append("this.").append(attr).append(EQUAL).append(attr).append(SEMICOLON).append(BR);
		attrSet.append(METHOD_RETRACT).append("}").append(BR);
		return attrSet.toString();
		
	}
	public static String genGet(Parameter param) {
		String attr = param.getAttr();
		String attrType = param.getAttrType();
		return genGet(attr,attrType);
	}
	
	public static String genGet(String attr,String attrType) {
		StringBuilder attrGet = new StringBuilder();
		attrGet.append(METHOD_RETRACT).append("public ").append(attrType).append(" get").append(StringUtil.firstUpperCase(attr)).append("() {").append(BR);
		attrGet.append(ATTR_RETRACT).append("return this.").append(attr).append(SEMICOLON).append(BR);
		attrGet.append(METHOD_RETRACT).append("}").append(BR);
		return attrGet.toString();
	}
	

}
