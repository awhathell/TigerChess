package com.model;

public class coach {

	
	private int id;
	private String cname;
	private String password;
	public coach() {
		super();
		// TODO Auto-generated constructor stub
	}
	public coach(String cname, String password) {
		super();
		this.cname = cname;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
