/**
 * Project Name:MyUtil <br>
 * File Name:MysqlConnection.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日上午10:45:46 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: MysqlConnection <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日上午10:45:46 <br>
 * @version
 * @since JDK 1.8
 */
public class MysqlConnection {

	public static Connection getConnection(String user,String pwd,String ip,String dbName) {
		String url = "jdbc:mysql://"+ip+":3306/"+dbName+"?characterEncoding=UTF-8";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

	