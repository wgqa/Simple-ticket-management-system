package com.entity;

public class User {

	// �û�id
	private int uida;
	// �û��˺�
	private String uname;
	// �û�����
	private String upwd;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getUida() {
		return uida;
	}

	public void setUida(int uida) {
		this.uida = uida;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public User(int uida, String uname, String upwd) {
		super();
		this.uida = uida;
		this.uname = uname;
		this.upwd = upwd;
	}

	public User() {
	}
}
