package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jdatabc.util.StringUtil;
import com.model.StuScore;
import com.model.User;

public class stuScoreDao {

	/**
	 * 添加学员成绩
	 * @param con
	 * @param stusc
	 * @return
	 * @throws Exception
	 */
	private static UserDao userdao=new UserDao();
	public int add(Connection con,StuScore stusc)throws Exception{
		String sql="insert into stuscore values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,stusc.getStuId() );
		pstmt.setInt(2,stusc.getExam1() );
		pstmt.setInt(3,stusc.getExam2() );
		pstmt.setInt(4,stusc.getExam3() );
		pstmt.setInt(5,stusc.getExam4() );
		pstmt.setInt(6,stusc.getGetLicence() );
		System.out.println(stusc.getStuId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询学员成绩
	 * @param con
	 * @param stusc
	 * @return
	 * @throws Exception
	 */
	public ResultSet allList(Connection con,StuScore stusc)throws Exception{//显示全部学员信息
		StringBuffer sb=new StringBuffer("select * from stuscore");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet list(Connection con,StuScore stusc)throws Exception{
		StringBuffer sb=new StringBuffer("select * from stuscore");
		if(!(StringUtil.isEmpty(String.valueOf(stusc.getStuId())))){//利用id查成绩
			sb.append(" where stuId like "+stusc.getStuId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet selfList(Connection con,StuScore stusc)throws Exception{
		StringBuffer sb=new StringBuffer("select * from stuscore");
		if(!(StringUtil.isEmpty(stusc.getStuName()))){
			sb.append(" where stuName like '%"+stusc.getStuName()+"%'");//利用用户名显示该用户成绩
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public static int update(Connection con, StuScore stuSc) throws Exception {
		String sqlSc="update stuscore set stuName=?,exam1=?,exam2=?,exam3=?,exam4=?,getLicence=? where stuId=?";
		String sqlUser="update t_user set userName=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sqlSc);
		PreparedStatement pstmt2=con.prepareStatement(sqlUser);
		pstmt.setString(1, stuSc.getStuName());
		pstmt.setInt(2, stuSc.getExam1());
		pstmt.setInt(3, stuSc.getExam2());
		pstmt.setInt(4, stuSc.getExam3());
		pstmt.setInt(5, stuSc.getExam4());
		int i;
		if(stuSc.getExam1()>90&&stuSc.getExam2()>90&&stuSc.getExam3()>90&&stuSc.getExam4()>90)
			i=1;
		else
			i=0;
		pstmt.setInt(6,i);
		pstmt.setInt(7, stuSc.getStuId());
		pstmt2.setString(1, stuSc.getStuName());
		pstmt2.setInt(2, stuSc.getStuId());
		pstmt2.executeUpdate();
		return pstmt.executeUpdate();
	}

	public static int delete(Connection con, int id) throws Exception {
		String sql="delete from stuscore where stuId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}

/*	public static int addStu(Connection con, StuScore stuSc) {
		String sql="insert into t_user values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();
	}*/

	public static int addStuSc(Connection con, StuScore stuSc) throws Exception {
		String sql="insert into stuscore values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, stuSc.getStuId());
		User currentUser=new User(stuSc.getStuId());
		User result=new User();
		result=userdao.findName(con, currentUser);
		if(result!=null){
			pstmt.setString(2, result.getUserName());
		}else
			return 3;//该学员还未注册
		StringBuffer sb=new StringBuffer("select * from stuscore where stuId like "+stuSc.getStuId());
		PreparedStatement pstmt1=con.prepareStatement(sb.toString());
		if(pstmt1.executeQuery().next())//该学员成绩信息已经存在
			return 2;
		pstmt.setInt(3, stuSc.getExam1());
		pstmt.setInt(4, stuSc.getExam2());
		pstmt.setInt(5, stuSc.getExam3());
		pstmt.setInt(6, stuSc.getExam4());
		int i;
		if(stuSc.getExam1()>90&&stuSc.getExam2()>90&&stuSc.getExam3()>90&&stuSc.getExam4()>90)
			i=1;
		else
			i=0;
		pstmt.setInt(7,i);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询学员名字
	 */
/*	public String findName(Connection con,int stuId)throws Exception{
		String sql="select * from t_user where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, stuId);
		ResultSet rs=pstmt.executeQuery();
		return rs.getString("userName");
		
	}*/
	/**
	 * 
	 */
	
	
	
}
