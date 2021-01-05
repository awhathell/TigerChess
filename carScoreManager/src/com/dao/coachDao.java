package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.model.coach;
import com.model.mg;

public class coachDao {

	
	public coach login(Connection con,coach co) throws SQLException{
		coach resultCo=null;
		String sql="select * from t_coach where cname=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, co.getCname());
		pstmt.setString(2, co.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultCo=new coach();
			resultCo.setCname(rs.getString("cname"));
			resultCo.setPassword(rs.getString("password"));
		}
		return resultCo;
	}
	
	public int addUser(Connection con,coach M) throws SQLException{
		//	mg resultM=null;
			String sql="select * from t_coach where cname=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, M.getCname());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return 2;
			}else{
				sql="insert into t_coach values(?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, M.getCname());
				pstmt.setString(2, M.getPassword());
				return pstmt.executeUpdate();
			}
		}
}
