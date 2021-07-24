package com.entity;

public class Hb {

	private int hbid;
	
	private String hbname;
	
	private String dj;
	
	private String gsmc;

	public Hb(int hbid, String hbname, String dj, String gsmc) {
		super();
		this.hbid = hbid;
		this.hbname = hbname;
		this.dj = dj;
		this.gsmc = gsmc;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public int getHbid() {
		return hbid;
	}

	public void setHbid(int hbid) {
		this.hbid = hbid;
	}

	public String getHbname() {
		return hbname;
	}

	public void setHbname(String hbname) {
		this.hbname = hbname;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public Hb() {
	}

}
