package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Fly;
import com.entity.User;
import com.util.DBhelper;

public class FlyDao{

	// 修改方法
	public int update(Fly fly) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement(
					"call updateFly(?,?,?,?,?)");
			ps.setDouble(1, fly.getJjg());
			ps.setString(2, fly.getJx());
			ps.setString(3, fly.getWlx());
			ps.setString(4, fly.getJtwz());
			ps.setInt(5,fly.getJid());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 删除方法
	public int delete(int id) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call deleteFly("+id+")");
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 增加方法
	public int add(Fly fly) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement(
					"call addFly(?,?,?,?,?,?,?)");
			ps.setDouble(1, fly.getJjg());
			ps.setString(2, fly.getJx());
			ps.setString(3, fly.getWlx());
			ps.setInt(4, fly.getHbid());
			ps.setString(5, fly.getSj());
			ps.setString(6, fly.getSpd());
			ps.setString(7, fly.getJtwz());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 根据条件，查询
	public List<Fly> getAll(String lm, String str) {
		List<Fly> ss = new ArrayList<Fly>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select * from fly where " + lm + " like '%" + str + "%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				Fly ta = new Fly();
				ta.setJid(rs.getInt(1));
				ta.setJjg(rs.getFloat(2));
				ta.setJx(rs.getString(3));
				ta.setWlx(rs.getString(4));
				ta.setHbid(rs.getInt(5));
				ta.setSj(rs.getString(6));
				ta.setSpd(rs.getString(7));
				ta.setJtwz(rs.getString(8));
				ss.add(ta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ss;
	}
	
	// 根据条件，查询单个
	public Fly getOne(int jid) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		Fly ta = new Fly();
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call getFly("+jid+")");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta.setJid(rs.getInt(1));
				ta.setJjg(rs.getFloat(2));
				ta.setJx(rs.getString(3));
				ta.setWlx(rs.getString(4));
				ta.setHbid(rs.getInt(5));
				ta.setSj(rs.getString(6));
				ta.setSpd(rs.getString(7));
				ta.setJtwz(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}

	// 根据条件，查询票数
	public int getPs(String gsmc,String sj) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		int ta=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select count(*) from fly,hb where fly.hbid=hb.hbid and fly.sj like '%"+sj+"%' and hb.gsmc like '%"+gsmc+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}
	
	// 根据条件，查询票价
	public float getPs(String gsmc,String sj,String wlx) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		float ta=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select fly.jjg from fly,hb where fly.hbid=hb.hbid and fly.sj like '%"+sj+"%' and hb.gsmc like '%"+gsmc+"%' and fly.wlx like '%"+wlx+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta=rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}
	
	// 根据条件，查询售票点数量
	public int getSpds(String gsmc) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		int ta=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select count(spd) from fly,hb where fly.hbid=hb.hbid and hb.gsmc like '%"+gsmc+"%' group by spd");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}
	
	// 根据条件，查询某航班售出额度
	public float getSce(String hbname) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		float ta=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select sum(fly.jjg) from fly,hb,ordera where ordera.jid=fly.jid and fly.hbid=hb.hbid and hb.hbname like '%"+hbname+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta=rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}
	
	// 根据条件，查询某航班某月售出额度
	public float getSce(String hbname,String month) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		float ta=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select sum(fly.jjg) from fly,hb,ordera where ordera.jid=fly.jid and fly.hbid=hb.hbid and hb.hbname like '%"+hbname+"%' and ordera.gmsj like '%"+month+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				ta=rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return ta;
	}

	
}
