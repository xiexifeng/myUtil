package com.xifeng.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;

import com.xifeng.util.db.MysqlConnection;

public class BwoilEdm {

	public static void updateInviteCode() {
		String cntSql = "select count(1) cnt from crew_edm_user where status=0";
		
		String querySql = "select id,email,invite_code from crew_edm_user where status=0 limit ?,50"; 
		int offset = 0;
		int pageSize = 50;
		int totalSize = 0;
		
		String updateSql = "update crew_edm_user set invite_code=?,status=? where id=?";
		String user="root";
		String pwd="root12345";
		String ip="192.168.102.82";
		String dbName="mol";
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		try {
			PreparedStatement cntStatement = conn.prepareStatement(cntSql);
			ResultSet rs = cntStatement.executeQuery();
			if(rs.next()) {
				totalSize = rs.getInt("cnt");
			}
			cntStatement.close();
			System.out.println(totalSize);
			int pageNum = totalSize/pageSize;
			if(totalSize/pageSize>0) {
				pageNum += 1;
			}
			String key = "Iloveyou";
			
			for(int i=0;i<pageNum;i++) {
				offset = i*pageSize;
				System.out.println(offset);
				PreparedStatement queryStatement = conn.prepareStatement(querySql);
				queryStatement.setInt(1, offset);
				ResultSet queryRs = queryStatement.executeQuery();
				while(queryRs.next()) {
					int id = queryRs.getInt("id");
					String email = queryRs.getString("email");
//					System.out.println(id+":"+email);
					PreparedStatement updateStatement = conn.prepareStatement(updateSql);
					String inviteCode = queryRs.getString("invite_code");
					int status = 1;
					if(CheckUtil.isEmail(email)) {
						inviteCode = RandomStringUtils.randomAlphanumeric(15);
					}else {
						status = 0;
					}
					updateStatement.setString(1, inviteCode);
					updateStatement.setInt(2, status);
					updateStatement.setInt(3, id);
					updateStatement.execute();
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void updateOther() {
		String cntSql = "select count(1) cnt from crew_edm_user where status=1";
		
		String querySql = "select id,email,invite_code from crew_edm_user where status=1 limit ?,50"; 
		
		String querySql1 = "select nickname,first_name,mobile FROM bigdata.cv_user_base_info where email=?"; 
		int offset = 0;
		int pageSize = 50;
		int totalSize = 0;
		
		String updateSql = "update crew_edm_user set name=?,phone=? where id=?";
		String user="root";
		String pwd="root12345";
		String ip="192.168.102.82";
		String dbName="mol";
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		try {
			PreparedStatement cntStatement = conn.prepareStatement(cntSql);
			ResultSet rs = cntStatement.executeQuery();
			if(rs.next()) {
				totalSize = rs.getInt("cnt");
			}
			cntStatement.close();
			System.out.println(totalSize);
			int pageNum = totalSize/pageSize;
			if(totalSize/pageSize>0) {
				pageNum += 1;
			}
			
			for(int i=0;i<pageNum;i++) {
				offset = i*pageSize;
				System.out.println(offset);
				PreparedStatement queryStatement = conn.prepareStatement(querySql);
				queryStatement.setInt(1, offset);
				ResultSet queryRs = queryStatement.executeQuery();
				while(queryRs.next()) {
					int id = queryRs.getInt("id");
					String email = queryRs.getString("email");
//					System.out.println(id+":"+email);
					
					
					PreparedStatement queryStatement1 = conn.prepareStatement(querySql1);
					queryStatement1.setString(1, email);
					ResultSet queryRs1 = queryStatement1.executeQuery();
					String name = null;
					String mobile = null;		
					if(queryRs1.next()) {
						name = queryRs1.getString("nickname");
						if(null == name || "".equals(name)) {
							name = queryRs1.getString("first_name");
						}
						mobile = queryRs1.getString("mobile");
					}
					
					PreparedStatement updateStatement = conn.prepareStatement(updateSql);
					updateStatement.setString(1, name);
					updateStatement.setString(2, mobile);
					updateStatement.setInt(3, id);
					updateStatement.execute();
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void importFromFile(String fileName) {
		String user="root";
		String pwd="root12345";
		String ip="192.168.102.82";
		String dbName="mol";
		String sql = "insert into crew_edm_user_filter (email) values(?)";
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		try {
			PreparedStatement insertStatement = conn.prepareStatement(sql);
			
			FileReader fr = new FileReader(fileName);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			while ((str = bf.readLine()) != null) {
				insertStatement.setString(1, str);
				insertStatement.executeUpdate();
			}

			bf.close();
			
		}catch(Exception e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
//		updateInviteCode();
		updateOther();
//		System.out.println(RandomStringUtils.randomAlphabetic(10));
//		System.out.println(RandomStringUtils.randomAlphanumeric(10));
//		String fileName = "C:\\Users\\xiezb\\Desktop\\crew.txt";
//		importFromFile(fileName);
//		System.out.println(CheckUtil.isEmail("adld33onn-et@hotmail.com"));
	}
}
