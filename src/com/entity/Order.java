package com.entity;

public class Order {

	// ����id
	private int oid;
	// �û�id
	private int uida;
	// ��Ʊid
	private int jid;
	// ����ʱ��
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
