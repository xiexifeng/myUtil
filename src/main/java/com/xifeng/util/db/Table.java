/**
 * Project Name:MyUtil <br>
 * File Name:Table.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日下午3:03:53 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.util.List;

/**
 * ClassName: Table <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日下午3:03:53 <br>
 * @version
 * @since JDK 1.8
 */
public class Table {
	String tableName;
	String tableComment;
	List<TableColumn> columnList;
	
	public Table() {
		
	}
	
	public Table(String tableName,List<TableColumn> columnList) {
		this.tableName = tableName;
		this.columnList = columnList;
	}
	public Table(String tableName,String tableComment,List<TableColumn> columnList) {
		this.tableName = tableName;
		this.tableComment = tableComment;
		this.columnList = columnList;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<TableColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<TableColumn> columnList) {
		this.columnList = columnList;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
}

	