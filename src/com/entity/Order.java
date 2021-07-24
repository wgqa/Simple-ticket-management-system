package com.entity;

public class Order {

	// 购买id
	private int oid;
	// 用户id
	private int uida;
	// 机票id
	private int jid;
	// 购买时间
	private String gmsj;
	
	public Order(int oid, int uida, int jid, String gmsj) {
		super();
		this.oid = oid;
		this.uida = uida;
		this.jid = jid;
		this.gmsj = gmsj;
	}
	
	

	public String getGmsj() {
		return gmsj;
	}



	public void setGmsj(String gmsj) {
		this.gmsj = gmsj;
	}



	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getUida() {
		return uida;
	}

	public void setUida(int uida) {
		this.uida = uida;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public String getgmsj() {
		return gmsj;
	}

	public void setgmsj(String gmsj) {
		this.gmsj = gmsj;
	}

	public Order() {
	}

}
