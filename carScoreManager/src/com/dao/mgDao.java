package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.mg;

public class mgDao {

	
	public mg login(Connection con,mg M) throws SQLException{
		mg resultM=null;
		String sql="select * from t_manager where mgname=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, M.getMgname());
		pstmt.setString(2, M.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultM=new mg();
			resultM.setMgname(rs.getString("mgname"));
			resultM.setPassword(rs.getString("password"));
		}
		return resultM;
	}
	public int addUser(Connection con,mg M) throws SQLException{
	//	mg resultM=null;
		String sql="select * from t_manager where mgname=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, M.getMgname());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return 2;
		}else{
			sql="insert into t_manager values(?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, M.getMgname());
			pstmt.setString(2, M.getPassword());
			return pstmt.executeUpdate();
		}
	}
}
