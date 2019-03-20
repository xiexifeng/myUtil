/**
 * Project Name:MyUtil <br>
 * File Name:AutoGen.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日下午6:34:52 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.sql.Connection;
import java.util.List;

import com.xifeng.util.StringUtil;

/**
 * ClassName: AutoGen <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日下午6:34:52 <br>
 * @version
 * @since JDK 1.8
 */
public class AutoGen {
	String projectName = "service-tradecenter";
	String className = "ConcurrencyOrderLog";
	String packageName = "com.caifubao.jcpt.tradecenter.dao.model.concurrency.order";
	String author = "xiezbmf";
//	String workspace = "E:/xiezhb/workspace5/";
	String workspace = "E:\\xiezhb\\git\\caifubao-jcpt\\dev\\caifubao-service\\";
//	String tableName = "i_order_log";
//	String mappingPath = "context/mapping";
	String mappingPath = "mapping/concurrency";
	String namespace = "com.caifubao.jcpt.tradecenter.dao.concurrency.order";
	String user = "xiezb_dev",pwd="ab683312",ip="10.50.10.201",dbName="dkd_sp2p";
	public void autoGen(String tableName) {
		PojoGen pojoGen = new PojoGen(projectName,className,packageName,author,workspace);
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		List<Table> tableList = TableDefine.getTableCols(conn, dbName,new String[] {tableName});
		pojoGen.genJavaFile(tableList.get(0));
		MybatisMapper mapper = new MybatisMapper(pojoGen,namespace,tableName,mappingPath);
		mapper.genMybatisMapper(tableList.get(0));
	}
	
	public void autoGen() {
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		List<Table> tableList = TableDefine.getTableCols(conn, dbName,null);
		for(Table table:tableList) {
			String tableName = table.getTableName();
			int i = tableName.indexOf("_");
			if(i>0) {
				className = StringUtil.underscoreToCamelCase(tableName.substring(i+1));
			}else {
				className = StringUtil.underscoreToCamelCase(tableName);
			}
			className = StringUtil.firstUpperCase(className);
			PojoGen pojoGen = new PojoGen(projectName,className,packageName,author,workspace);
			pojoGen.genJavaFile(table);
			MybatisMapper mapper = new MybatisMapper(pojoGen,namespace,tableName,mappingPath);
			mapper.genMybatisMapper(table);
		}
		
	}
	public static void main(String[] args) {
		String tableName = "i_order_log";
		new AutoGen().autoGen(tableName);
	}
}

	