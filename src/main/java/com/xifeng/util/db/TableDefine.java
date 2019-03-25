/**
 * Project Name:MyUtil <br>
 * File Name:TableDefine.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日上午10:49:06 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xifeng.util.RegexChk;

/**
 * ClassName: TableDefine <br>
 * Description: TODO
 * 
 * @author xiezbmf
 * @Date 2018年5月28日上午10:49:06 <br>
 * @version
 * @since JDK 1.8
 */
public class TableDefine {
	public static List<String> getTables(Connection conn, String dbName) {
		List<String> dataSet = new ArrayList<String>();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			int count = 0;
			ResultSet resultSet = dbmd.getTables(dbName, "%", null, new String[] { "TABLE" });
			while (resultSet.next()) {
				count++;
				String tableName = resultSet.getString("TABLE_NAME");
				dataSet.add(tableName);
			}
			System.out.println("export table target ,success:" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSet;
	}
	public static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        try {
            comment = new String(comment.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return comment;
    }

	public static String getTableComment(Connection conn, String table) {
		String tableComment = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + table);
			if (rs != null && rs.next()) {
				String create = rs.getString(2);
				tableComment = parse(create);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tableComment;
	}

	private static boolean isSplitTable(String dbName) {
//		return RegexChk.startCheck("([a-zA-Z]+[\\_]?[a-zA-Z]?)+\\_[0-9]+", dbName);
		long start = System.currentTimeMillis();
		boolean res = !RegexChk.startCheck("([a-zA-Z]+[\\_]?[a-zA-Z]?)+", dbName);
		System.out.println("cost time:" + (System.currentTimeMillis() - start) + "ms");
		return res;
	}

	public static List<Table> getTableCols(Connection conn, String dbName, String[] tables) {
		if (tables == null || tables.length == 0) {
			tables = new String[] {};
			tables = getTables(conn, dbName).toArray(tables);
		}
		List<Table> tableList = new ArrayList<>();
		ResultSet rs = null;
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			int count = 0;
			for (String table : tables) {
				System.out.println("table:" + table);
				if (isSplitTable(table)) {
					continue;
				}
				ResultSet resultSet = dbmd.getTables(dbName, "%", table, new String[] { "TABLE" });
				List<TableColumn> tableColumnList = new ArrayList<>();
				String tableComment =  getTableComment(conn, table); 
				while (resultSet.next()) {
					count++;
					String tableName = resultSet.getString("TABLE_NAME");
					rs = dbmd.getColumns(null, "%", tableName, "%");
					System.out.println("表名：" + tableName + ",表注释:" + tableComment + "\t\n表字段信息：");
					while (rs.next()) {
						String colName = rs.getString("COLUMN_NAME");
						String marks = rs.getString("REMARKS").trim();
						String typeName = rs.getString("TYPE_NAME");
						String colSize = rs.getString("COLUMN_SIZE");
						String isNull = rs.getString("IS_NULLABLE");
						String precision = rs.getString("DECIMAL_DIGITS");
						String columnDef = rs.getString("COLUMN_DEF");
						columnDef = columnDef == null ? "无" : columnDef;

						System.out.println(
								colName + "----" + typeName + "--" + isNull + "---" + marks + "----" + precision);
						TableColumn tableColumn = new TableColumn(colName, typeName, colSize, isNull, precision,
								columnDef, marks);
						tableColumnList.add(tableColumn);
					}
				}
				System.out.println("++-------------------------++");
				System.out.println("export table target :" + tables.length + ",success:" + count);
				tableList.add(new Table(table, tableComment, tableColumnList));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}

	public static void main(String[] args) {
//		String user = "xiezb_dev",pwd="ab683312",ip="10.50.10.201",dbName="jcpt_data_fix";
//		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
//		List<String> tables = TableDefine.getTables(conn, dbName);
//		System.out.println(tables);
//		List<Table> tableList = TableDefine.getTableCols(conn, dbName,null);
//		System.out.println(tableList);
		String table = "t_third_supplier_account_detail-过时";
		table = "t_third_supplier_account_detail-";
		table = "t_third_supplier_account_detail过时";
//		table = "ddd";
		long start = System.currentTimeMillis();
		String reg = "([a-zA-Z]+[\\_]?[a-zA-Z]?)+^[\u4e00-\u9fa5]";
		boolean res = !RegexChk.startCheck(reg, table);
		System.out.println("cost time:" + (System.currentTimeMillis() - start) + "ms,res=" + res);
	}
}
