package com.hibernate.demo;

public class User {

	private String userName;  //用户名
	private String password;  //用户密码
	private String mail;     //用户邮箱
	
	//hibernate规定要作为持久化类必须实现一个默认的构造方法
	public User()
	{
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
