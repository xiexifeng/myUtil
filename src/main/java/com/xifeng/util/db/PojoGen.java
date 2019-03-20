/**
 * Project Name:MyUtil <br>
 * File Name:PojoGen.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日上午11:32:16 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.util.Date;
import java.util.List;

import com.xifeng.util.StringUtil;
import com.xifeng.util.date.DateUtil;
import com.xifeng.util.io.FileUtil;

/**
 * ClassName: PojoGen <br>
 * Description: TODO
 * 
 * @author xiezbmf
 * @Date 2018年5月28日上午11:32:16 <br>
 * @version
 * @since JDK 1.8
 */
public class PojoGen {
	public static String METHOD_RETRACT = "    ";
	public static String ATTR_RETRACT = "        ";
	public static String BR = "\n";

	public static String switchType(String typeName) {
		if (typeName.contains("INT")) {
			return "Integer";
		}
		if (typeName.contains("CHAR") || typeName.contains("TEXT")) {
			return "String";
		}
		if (typeName.contains("DECIMAL")) {
			return "BigDecimal";
		}
		if (typeName.contains("DATE") || typeName.contains("TIME")) {
			return "Date";
		}
		if (typeName.contains("BLOB")) {
			return "Byte[]";
		}
		return "String";

	}

	String projectName;
	String className;
	String packageName;
	String author;
	String copyright = "(c) 2017, 深圳市彩付宝科技有限公司 All Rights Reserved";
	String workspace;

	public PojoGen() {

	}

	public PojoGen(String projectName, String className, String packageName, String author, String workspace) {
		this.projectName = projectName;
		this.className = className;
		this.packageName = packageName;
		this.author = author;
		this.workspace = workspace;
	}

	public PojoGen(String projectName, String className, String packageName, String author, String workspace,
			String copyright) {
		this(projectName, className, packageName, author, workspace);
		this.copyright = copyright;
	}

	public String getJavaFileDefineTitle() {
		String fileDefine = "/**" + BR + "* Project Name:" + projectName + " <br>" + BR + "* File Name:" + className
				+ ".java <br>" + BR + "* Package Name:" + packageName + " <br>" + BR + "* @author " + author + BR
				+ "* Date:" + DateUtil.format(new Date(), "yyyy年MM月dd日 a HH:mm:ss") + " <br>" + BR + "* Copyright "
				+ copyright + "." + BR + "*/" + BR;
		return fileDefine;
	}

	/**
	 * ClassName: AutoGenerateModel <br>
	 * Description: TODO
	 * 
	 * @author xiezbmf
	 * @Date 2018年5月28日上午10:48:47 <br>
	 * @version
	 * @since JDK 1.8
	 */
	public String getJavaClassFileAnnotation() {
		String fileDefine = "/**" + BR + "* ClassName:" + className + " <br>" + "\n" + "* Description: TODO" + "\n"
				+ "* @author " + author + "\n" + "* @Date:" + DateUtil.format(new Date(), "yyyy年MM月dd日 a HH:mm:ss")
				+ " <br>" + "\n" + "* @version " + "\n" + "* @since JDK 1.8 " + "\n" + "*/" + BR;
		return fileDefine;
	}

	public void genJavaFile(Table table) {

		StringBuilder sb = new StringBuilder();
		sb.append(this.getJavaFileDefineTitle());
		sb.append("package ").append(this.getPackageName()).append(";").append(BR);
		sb.append(BR);
		boolean dateFlag = false;
		boolean decimalFlag = false;
		List<TableColumn> columnList = table.getColumnList();
		for (TableColumn column : columnList) {
			String type = switchType(column.getTypeName());
			if (type.contains("Date")) {
				dateFlag = true;
			}
			if (type.contains("BigDecimal")) {
				decimalFlag = true;
			}
		}
		if (decimalFlag) {
			sb.append("import java.math.BigDecimal;").append(BR);
		}
		if (dateFlag) {
			sb.append("import java.util.Date;").append(BR);
		}
		sb.append(BR).append("import org.apache.ibatis.type.Alias;").append(BR);
		sb.append(this.getJavaClassFileAnnotation());
		sb.append("@Alias(\"").append(StringUtil.firstLowerCase(this.className)).append("\")").append(BR);
		sb.append("public class ").append(this.className).append("{").append(BR);
		for (TableColumn column : columnList) {
			String type = switchType(column.getTypeName());
			String attrName = StringUtil.underscoreToCamelCase(column.getColumnName());
			sb.append(this.getFieldComment(attrName, column.getRemark()));
			sb.append(METHOD_RETRACT).append(type).append(" ").append(attrName).append(";").append(BR);
		}
		sb.append(BR);
		for (TableColumn column : columnList) {
			String type = switchType(column.getTypeName());
			String attrName = StringUtil.underscoreToCamelCase(column.getColumnName());
			sb.append(METHOD_RETRACT).append("public ").append(type).append(" get")
					.append(StringUtil.firstUpperCase(attrName)).append("(){").append(BR);
			sb.append(ATTR_RETRACT).append("return this.").append(attrName).append(";").append(BR);
			sb.append(METHOD_RETRACT).append("}").append(BR);

			sb.append(METHOD_RETRACT).append("public void set").append(StringUtil.firstUpperCase(attrName)).append("(")
					.append(type).append(" ").append(attrName).append("){").append(BR);
			sb.append(ATTR_RETRACT).append("this.").append(attrName).append(" = ").append(attrName).append(";")
					.append(BR);
			sb.append(METHOD_RETRACT).append("}").append(BR);
		}
		sb.append("}");
		String genPath = this.workspace + this.projectName + "/src/main/java/" + this.packageName.replaceAll("\\.", "/")
				+ "/" + this.className + ".java";
		FileUtil.writeFile(genPath, sb.toString());
	}

	public String getFieldComment(String field, String comment) {
		String str = METHOD_RETRACT + "/**" + BR + METHOD_RETRACT + " * " + field + ":" + comment +"."+ BR + METHOD_RETRACT
				+ " */" + BR;
		return str;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
}
