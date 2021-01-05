package com.jdatabc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类
 * @author w1585
 *
 */
public class DbUtil {

	
	private String dbUrl="jdbc:mysql://localhost:3307/db_carscore";
	private String dbUserName="root";
	private String dbPassword="123789";
	private String jdbcName="com.mysql.jdbc.Driver";
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	/**
	 * 关闭连接
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args){
		DbUtil dbutil=new DbUtil();
		try {
			dbutil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		
		
		
	}
	
}
