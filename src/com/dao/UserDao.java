package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.entity.Fly;
import com.entity.User;
import com.util.DBhelper;

public class UserDao{

	// �û���½����
	public User login(User user) {
		User u = new User();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call checkUser(?,?)");
			ps.setString(1, user.getUname());
			ps.setString(2, user.getUpwd());
			rs = ps.executeQuery();
			if (rs.next()) {
				u.setUida(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setUpwd(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return u;
	}

	// ��ѯ�û��ķ���
	public List<User> getAll(String str) {
		List<User> mys=new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("select * from usera where uname like '%"+str+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUida(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setUpwd(rs.getString(3));
				mys.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return mys;
	}
	
	// �û�ע�᷽��
	public int add(User user) {
		int n = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBhelper.getCon();
			ps = con.prepareStatement("call addUser(?,?)");
			ps.setString(1, user.getUname());
			ps.setString(2, user.getUpwd());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBhelper.myClose(con, ps, rs);
		}

		return n;
	}
	
}
