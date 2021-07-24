package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Fly;
import com.entity.Order;
import com.entity.User;
import com.util.DBhelper;

public class OrderDao{

	// 增加记录
	public int add(Order order) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call addOrder(?,?,?)");
			ps.setInt(1, order.getUida());
			ps.setInt(2, order.getJid());
			ps.setString(3, order.getGmsj());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}

	// 查询所有记录
	public List<Order> getAll() {
		List<Order> myl = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call getAllOrder");
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOid(rs.getInt(1));
				order.setUida(rs.getInt(2));
				order.setJid(rs.getInt(3));
				order.setGmsj(rs.getString(4));
				myl.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return myl;
	}

	// 根据指定用户查询记录
	public List<Order> selectByUname(int id) {
		List<Order> myl = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call getAllOrderByuid("+id+")");
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOid(rs.getInt(1));
				order.setUida(rs.getInt(2));
				order.setJid(rs.getInt(3));
				order.setGmsj(rs.getString(4));
				myl.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return myl;
	}
	
	// 查询某代售点某月售出票数金额
	public float select(String address,String month) {
		float sum=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select sum(fly.jjg) from ordera,fly where fly.jid=ordera.jid and fly.spd="+address+" and ordera.gmsj like '%"+month+"%' ");
			rs = ps.executeQuery();
			while (rs.next()) {
				sum=rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}
		return sum;
	}

}
