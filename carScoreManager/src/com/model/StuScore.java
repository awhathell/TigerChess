package com.model;
/**
 * ѧԱ�ɼ���
 * @author w1585
 *
 */
public class StuScore {

	private int id; // ���
	private int stuId; // ѧ�����
	private String stuName;
	private int exam1; // ��Ŀ1
	private int exam2; // ��Ŀ2
	private int exam3; // ��Ŀ3
	private int exam4; // ��Ŀ��
	private int getLicence;//�Ƿ��õ�����
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
	public StuScore(int stuId){//�����û�id��ѯ�ɼ�
		super();
		this.stuId = stuId;
	}
	public StuScore(int stuId,String stuName){//�����û�id��ѯ�ɼ�
		super();
		this.stuId = stuId;
		this.stuName = stuName;
	}
	public StuScore(String stuName){//��;��ѧ��ʹ���û��������½ϵͳ�������û�����ѯ�ɼ�
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
