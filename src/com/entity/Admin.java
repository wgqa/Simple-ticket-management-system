package com.entity;

import java.io.Serializable;

public class Admin implements Serializable {

	/**
	 * ����Ա
	 */

	private String azh;// �˺�
	private String apwd;// ����

	public String getAzh() {
		return azh;
	}

	public void setAzh(String azh) {
		this.azh = azh;
	}

	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	
	public Admin(String azh, String apwd) {
		super();
		this.azh = azh;
		this.apwd = apwd;
	}

	public Admin() {
	}

}
