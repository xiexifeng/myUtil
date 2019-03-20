/**
 * Project Name:MyUtil <br>
 * File Name:PojoGen.java <br>
 * Package Name:com.xifeng.util.test <br>
 * @author xiezbmf
 * Date:2018年5月28日下午2:55:05 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.test;

import com.xifeng.util.StringUtil;

/**
 * ClassName: PojoGen <br>
 * Description: TODO
 * 
 * @author xiezbmf
 * @Date 2018年5月28日下午2:55:05 <br>
 * @version
 * @since JDK 1.8
 */
public class PojoGenTest {
	public static void main(String[] args) {
//		String projectName = "MyUtil";
//		String className = "FlowLog";
//		String packageName = "com.xifeng.util.pojo";
//		String author = "xiezbmf";
//		String workspace = "E:/xiezhb/workspace-util/";
//		String tableName = "fd_flow_log";
//		String mappingPath = "context/mapping";
//		String namespace = "com.xifeng.util.dao";
//		PojoGen pojoGen = new PojoGen(projectName,className,packageName,author,workspace);
//		String user = "xiezb_dev",pwd="ab683312",ip="10.50.10.201",dbName="jcpt_data_fix";
//		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
//		List<Table> tableList = TableDefine.getTableCols(conn, dbName,new String[] {"fd_flow_log"});
//		pojoGen.genJavaFile(tableList.get(0));
//		
//		MybatisMapper mapper = new MybatisMapper(pojoGen,namespace,tableName,mappingPath);
//		mapper.genMybatisMapper(tableList.get(0));
		
		String tableName = "fd_flow_log";
		int i = tableName.indexOf("_");
		if(i>0) {
			tableName = StringUtil.underscoreToCamelCase(tableName.substring(i+1));
		}
		System.out.println(tableName);
	}
}
