package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdatabc.util.StringUtil;
import com.model.StuScore;
import com.model.User;
import com.model.coach;

/**
 * ”√ªßDao¿‡
 * @author w1585
 *
 */
public class UserDao {

	
	public User login(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
	public ResultSet list(Connection con,User stusc)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user where stuId like '%"+stusc.getId()+"%'");
	/*	if(!(StringUtil.isEmpty(String.valueOf(stusc.getStuId())))){
			sb.append(" where stuId like '%"+stusc.getStuId()+"%'");
		}*/
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public User findName(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from t_user where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, user.getId());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
		}
		return resultUser;
	}
	
	public int addUser(Connection con,User M) throws SQLException{
		//	mg resultM=null;
			String sql="select * from t_user where userName=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, M.getUserName());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return 2;
			}else{
				sql="insert into t_user values(null,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, M.getUserName());
				pstmt.setString(2, M.getPassword());
				return pstmt.executeUpdate();
			}
		}
}
