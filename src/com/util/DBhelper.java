package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBhelper {

	/**
	 * �����ݿ�İ�����
	 */
   
	private final static String CNAME="com.mysql.jdbc.Driver";
	private final static String URL="jdbc:mysql://localhost:3306/wang?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
	private final static String NAME="root";
	private final static String PWD="mousesys";
	
	static {
		try {
		Class.forName(CNAME);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�������ݿ�ķ���
	public static Connection getCon(){
		Connection con=null;
		try {
			con=DriverManager.getConnection(URL,NAME ,PWD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	//�ر����ӵķ���
	public static void myClose(Connection con,PreparedStatement ps,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(con!=null&&!con.isClosed()){
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
