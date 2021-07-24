package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.entity.Hb;
import com.entity.User;
import com.util.DBhelper;

public class HbDao {

	// 修改方法
	public int update(Hb hb) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call updateHb(?,?,"+hb.getHbid()+")");
			ps.setString(1, hb.getHbname());
			ps.setString(2, hb.getDj());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 删除方法
	public int delete(int hbid) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call deleteHb("+hbid+")");
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 增加方法
	public int add(Hb hb) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call addHb(?,?,?)");
			ps.setString(1, hb.getHbname());
			ps.setString(2, hb.getDj());
			ps.setString(3, hb.getGsmc());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 根据条件，查询单个
	public Hb getOne(int hbid) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		Hb s = new Hb();
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call getHb(" + hbid+")");
			rs = ps.executeQuery();
			while (rs.next()) {
				s.setHbid(rs.getInt(1));
				s.setHbname(rs.getString(2));
				s.setDj(rs.getString(3));
				s.setGsmc(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return s;
	}

	// 查询全部
	public List<Hb> getAll(String name) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		List<Hb> cs = new ArrayList<Hb>();
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select * from hb where hbname like '%"+name+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				Hb s = new Hb();
				s.setHbid(rs.getInt(1));
				s.setHbname(rs.getString(2));
				s.setDj(rs.getString(3));
				s.setGsmc(rs.getString(4));
				cs.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return cs;
	}
	
	// 某个航空公司有多少航班
	public int getHbs(String gsmc) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		int hbs=0;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select count(*) from hb where gsmc like '%"+gsmc+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				hbs=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return hbs;
	}
	

}
