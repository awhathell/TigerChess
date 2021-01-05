package com.jdatabc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݿ⹤����
 * @author w1585
 *
 */
public class DbUtil {

	
	private String dbUrl="jdbc:mysql://localhost:3307/db_carscore";
	private String dbUserName="root";
	private String dbPassword="123789";
	private String jdbcName="com.mysql.jdbc.Driver";
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	/**
	 * �ر�����
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
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
		
		
		
	}
	
}
