package com.model;
/**
 * 学员成绩类
 * @author w1585
 *
 */
public class StuScore {

	private int id; // 编号
	private int stuId; // 学生编号
	private String stuName;
	private int exam1; // 科目1
	private int exam2; // 科目2
	private int exam3; // 科目3
	private int exam4; // 科目四
	private int getLicence;//是否拿到驾照
	public StuScore() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StuScore(int stuId,String stuName, int exam1, int exam2, int exam3, int exam4) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.exam1 = exam1;
		this.exam2 = exam2;
		this.exam3 = exam3;
		this.exam4 = exam4;
		if(exam1>90&&exam2>90&&exam3>90&&exam4>90){
			this.getLicence=1;
		}else{
			this.getLicence=0;
		}
	}
	public StuScore(int stuId){//利用用户id查询成绩
		super();
		this.stuId = stuId;
	}
	public StuScore(int stuId,String stuName){//利用用户id查询成绩
		super();
		this.stuId = stuId;
		this.stuName = stuName;
	}
	public StuScore(String stuName){//用途：学生使用用户名密码登陆系统，利用用户名查询成绩
		super();
		this.stuName = stuName;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getExam1() {
		return exam1;
	}
	public void setExam1(int exam1) {
		this.exam1 = exam1;
	}
	public int getExam2() {
		return exam2;
	}
	public void setExam2(int exam2) {
		this.exam2 = exam2;
	}
	public int getExam3() {
		return exam3;
	}
	public void setExam3(int exam3) {
		this.exam3 = exam3;
	}
	public int getExam4() {
		return exam4;
	}
	public void setExam4(int exam4) {
		this.exam4 = exam4;
	}
	public int getGetLicence() {
		return getLicence;
	}
	public void setGetLicence(int getLicence) {
		this.getLicence = getLicence;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	
}
