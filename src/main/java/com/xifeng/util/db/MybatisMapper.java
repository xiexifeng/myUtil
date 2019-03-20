/**
 * Project Name:MyUtil <br>
 * File Name:MybatisMapper.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日上午11:00:15 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.util.List;

import com.xifeng.util.StringUtil;
import com.xifeng.util.io.FileUtil;

/**
 * ClassName: MybatisMapper <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日上午11:00:15 <br>
 * @version
 * @since JDK 1.8
 */
public class MybatisMapper {
	public static String BR="\n";
	public static String METHOD_RETRACT="    ";
	public static String ATTR_RETRACT="        ";
	public MybatisMapper() {}
	
	PojoGen pojoGen;
	String namespace;
	String dao;
	String tableName;
	String mappingPath;
	
	public MybatisMapper(PojoGen pojoGen, String namespace,	String tableName,String mappingPath) {
		this.pojoGen = pojoGen;
		this.namespace = namespace;
		this.dao = pojoGen.getClassName()+"Dao";
		this.tableName = tableName;
		this.mappingPath = mappingPath;
	}
	
	public String getMybatisHead() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(BR);
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append(BR);
		sb.append("<mapper namespace=\"").append(namespace).append(".").append(dao).append("\">").append(BR);
		return sb.toString();
	}
	
	public void genMybatisMapper(Table table) {
		StringBuilder sb = new StringBuilder();
		String className = pojoGen.getClassName();
		String firstLowerClassName = StringUtil.firstLowerCase(className);
		sb.append(this.getMybatisHead());
		sb.append(METHOD_RETRACT).append("<resultMap id=\"FullResultMap\" type=\"").append(firstLowerClassName).append("\">").append(BR);
		List<TableColumn> columnList = table.getColumnList();
		for (TableColumn column : columnList) {
			String attrName = StringUtil.underscoreToCamelCase(column.getColumnName());
			sb.append(ATTR_RETRACT).append("<result property=\"").append(attrName).append("\" column=\"").append(column.getColumnName()).append("\" />").append(BR);
		}
		sb.append(METHOD_RETRACT).append("</resultMap>").append(BR);
		String[] funcs = new String[4];
		/*insert*/
		funcs[0] = "int save"+className+"("+className+" "+firstLowerClassName+");";
		StringBuilder insertSb = new StringBuilder();
		insertSb.append(METHOD_RETRACT).append("<insert id=\"save").append(className).append("\" parameterType=\"").append(firstLowerClassName).append("\" >").append(BR);
		insertSb.append(METHOD_RETRACT).append("INSERT INTO ").append(tableName).append("(");
		int c = 0;
		int len = columnList.size();
		for (;c<len;c++) {
			insertSb.append(columnList.get(c).getColumnName());
			if(c<len-1){
				insertSb.append(",");
			}
			if(c%5==0){
				insertSb.append(BR).append(METHOD_RETRACT);
			}
		}
		insertSb.append(")").append(BR);
		insertSb.append(METHOD_RETRACT).append(" VALUES (");
		for (c=0;c<len;c++) {
			String attrName = StringUtil.underscoreToCamelCase(columnList.get(c).getColumnName());
			insertSb.append("#{").append(attrName).append("}");
			if(c<len-1){
				insertSb.append(",");
			}
			if(c%5==0){
				insertSb.append(BR).append(METHOD_RETRACT);
			}
		}
		insertSb.append(")").append(BR);
		insertSb.append(METHOD_RETRACT).append("</insert>").append(BR);
		
		/*update*/
		funcs[1] = "int update"+className+"ById("+className+" "+firstLowerClassName+");";
		StringBuilder updateSb = new StringBuilder();
		updateSb.append(METHOD_RETRACT).append("<update id=\"update").append(className).append("ById\" parameterType=\"").append(firstLowerClassName).append("\" >").append(BR);
		updateSb.append(METHOD_RETRACT).append("update ").append(tableName).append(" set update_time=#{updateTime}").append(BR).append(METHOD_RETRACT);
		String colId = columnList.get(0).getColumnName();
		String id = StringUtil.underscoreToCamelCase(colId);
	    for (c=0;c<len;c++) {
	    	String colName = columnList.get(c).getColumnName();
	    	String attrName = StringUtil.underscoreToCamelCase(colName);
	    	String type = PojoGen.switchType(columnList.get(c).getTypeName());
	    	if(type.equals("String")) {
	    		updateSb.append("<if test=\"").append(attrName).append(" !=null and ").append(attrName).append(" !=''\">");
	    	}else {
	    		updateSb.append("<if test=\"").append(attrName).append(" !=null ").append("\">");
	    	}
	    	updateSb.append(",").append(colName).append("=#{").append(attrName).append("}");
	    	updateSb.append("</if>").append(BR).append(METHOD_RETRACT);
	    }
	    updateSb.append(" where ").append(colId).append("=#{").append(id).append("}").append(BR).append(METHOD_RETRACT);
	    updateSb.append("</update>").append(BR);
	    /*delete*/
	    funcs[2] = "int delete"+className+"ById(@Param(\""+id+"\")String "+id+");";
	    StringBuilder deleteSb = new StringBuilder();
	    deleteSb.append(METHOD_RETRACT).append("<delete id=\"delete").append(className).append("ById\">").append("\n");
	    deleteSb.append(METHOD_RETRACT).append("delete from ").append(tableName).append(" where ").append(colId).append("=#{").append(id).append("}").append(BR);
	    deleteSb.append(METHOD_RETRACT).append("</delete>").append(BR);
	    
	    /*select*/
	    funcs[3] = "List<"+className+"> query"+className+"ByParams(Map<String,String> params);";
	    StringBuilder selectSb = new StringBuilder();
	    selectSb.append(METHOD_RETRACT).append("<select id=\"query").append(className).append("ByParams\" resultMap=\"FullResultMap\" parameterType=\"map\" >").append(BR);
	    selectSb.append(METHOD_RETRACT).append("select ");
	    c = 0;
	    for (;c<len;c++) {
	    	selectSb.append(columnList.get(c).getColumnName());
			if(c<len-1){
				selectSb.append(",");
			}
			if(c%5==0){
				selectSb.append(BR).append(METHOD_RETRACT);
			}
	    }
	    selectSb.append(" from ").append(tableName).append(" where 1=1").append(BR).append(METHOD_RETRACT);
	    for (c=0;c<len;c++) {
	    	String colName = columnList.get(c).getColumnName();
	    	String attrName = StringUtil.underscoreToCamelCase(colName);
	    	selectSb.append("<if test=\"").append(attrName).append(" !=null and ").append(attrName).append(" !=''\">");
	    	selectSb.append(" and ").append(colName).append("=#{").append(attrName).append("}");
	    	selectSb.append("</if>").append(BR).append(METHOD_RETRACT);
	    }
	    selectSb.append("</select>").append(BR);
	    
	    sb.append(insertSb).append(updateSb).append(deleteSb).append(selectSb);
		sb.append("</mapper>");
		String filePath = pojoGen.getWorkspace()+pojoGen.getProjectName()+"/src/main/resources/"+this.mappingPath+"/"+this.dao+".xml";
		FileUtil.writeFile(filePath, sb.toString());
		
		String pojo = pojoGen.getPackageName()+"."+pojoGen.getClassName();
		pojoGen.setClassName(dao);
		pojoGen.setPackageName(namespace);
		this.createInterfaceFile(pojoGen, pojo, funcs);
	}
	
	public void createInterfaceFile(PojoGen pojoGen,String pojo,String[] funcs){
	    StringBuilder sb = new StringBuilder();
	    sb.append(pojoGen.getJavaFileDefineTitle());
		sb.append("package ").append(pojoGen.getPackageName()).append(";").append(BR);
		sb.append(BR);
		sb.append("import java.util.List;").append(BR);
		sb.append("import java.util.Map;").append(BR);
		sb.append(BR);
		sb.append("import org.apache.ibatis.annotations.Param;").append(BR);
		sb.append("import org.springframework.stereotype.Repository;").append(BR);
		sb.append(BR);
		sb.append("import ").append(pojo).append(";").append(BR);
		sb.append(pojoGen.getJavaClassFileAnnotation());
		sb.append("@Repository").append(BR);
		sb.append("public interface ").append(dao).append("{").append(BR);
	    for(String func:funcs){
	      sb.append(METHOD_RETRACT).append(func).append(BR);
	      sb.append(BR);
	    }
	    sb.append("}");
	    String filePath = pojoGen.getWorkspace()+pojoGen.getProjectName()+"/src/main/java/"+pojoGen.getPackageName().replaceAll("\\.", "/")+"/"+pojoGen.getClassName()+".java";
	    FileUtil.writeFile(filePath, sb.toString());
	  }

	public PojoGen getPojoGen() {
		return pojoGen;
	}

	public void setPojoGen(PojoGen pojoGen) {
		this.pojoGen = pojoGen;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}

	