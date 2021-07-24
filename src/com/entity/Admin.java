package com.entity;

import java.io.Serializable;

public class Admin implements Serializable {

	/**
	 * π‹¿Ì‘±
	 */

	private String azh;// ’À∫≈
	private String apwd;// √‹¬Î

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
