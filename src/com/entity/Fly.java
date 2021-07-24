package com.entity;

public class Fly {

	// 机票id
	private int jid;
	// 价格
	private float jjg;
	// 飞机型号
	private String jx;
	// 座位类型
	private String wlx;
	// 航班
	private int hbid;
	// 时间
	private String sj;
	// 售票点
	private String spd;
	// 具体位置
	private String jtwz;

	public Fly(int jid, float jjg, String jx, String wlx, int hbid, String sj, String spd, String jtwz) {
		super();
		this.jid = jid;
		this.jjg = jjg;
		this.jx = jx;
		this.wlx = wlx;
		this.hbid = hbid;
		this.sj = sj;
		this.spd = spd;
		this.jtwz = jtwz;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getSpd() {
		return spd;
	}

	public void setSpd(String spd) {
		this.spd = spd;
	}

	public String getJtwz() {
		return jtwz;
	}

	public void setJtwz(String jtwz) {
		this.jtwz = jtwz;
	}

	public int getHbid() {
		return hbid;
	}

	public void setHbid(int hbid) {
		this.hbid = hbid;
	}

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public float getJjg() {
		return jjg;
	}

	public void setJjg(float jjg) {
		this.jjg = jjg;
	}

	public String getJx() {
		return jx;
	}

	public void setJx(String jx) {
		this.jx = jx;
	}

	public String getWlx() {
		return wlx;
	}

	public void setWlx(String wlx) {
		this.wlx = wlx;
	}

	public Fly() {
	}

}
