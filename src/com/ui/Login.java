package com.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.dao.AdminDao;
import com.dao.UserDao;
import com.entity.Admin;
import com.entity.User;

public class Login extends JFrame {

	public static void main(String[] args) {
		try {
			// 设置界面风格
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 新建一个Login类
		new Login();
	}

	// 放标题的容器
	private JPanel jpn = new JPanel();
	// 放用户名，密码，登陆，取消按钮的容器
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("用户名:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlb = new JLabel("密  码:");
	private JPasswordField jpf = new JPasswordField(16);
	private JLabel jle = new JLabel("用户身份:");
	// 下拉框
	private JComboBox jcb = new JComboBox();

	private JButton jba = new JButton("登陆");
	private JButton jbb = new JButton("注册");

	// new一个dao类
	UserDao ud = new UserDao();

	// 总容器
	private JPanel jpz = new JPanel();

	public Login() {
		this.setTitle("登录界面");
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);// 设置窗体无法改变大小
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		// 将下拉框里面加值
		jcb.addItem("管理员登录");
		jcb.addItem("用户登录");

		// 用户名以及其文本框的位置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(jla, gbc);// 让约束对象来约束jla(坐标)
		jpa.add(jla);// 将jla增加到容器中

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jtf, gbc);
		jpa.add(jtf);

		// 密码以及其文本框的位置
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(jlb, gbc);
		jpa.add(jlb);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(jpf, gbc);
		jpa.add(jpf);

		// 用户类型以及其下拉框的位置
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jle, gbc);
		jpa.add(jle);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(jcb, gbc);
		jpa.add(jcb);

		// 按钮位置
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// 标题
		JLabel jlc = new JLabel("机票购买管理系统！");
		jlc.setFont(new Font("宋体", Font.BOLD, 20));
		jpn.add(jlc);

		// 登陆方法
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取对应文本框的值
				String uname = jtf.getText();
				// 获得密码框的值
				String upwd = jpf.getText();
				// 获得下拉框的值
				String jcbstr = jcb.getSelectedItem().toString();
				if (jcbstr.equals("管理员登录")) {
					// 调用dao方法，获得影响行数
					boolean f = new AdminDao().check(uname, upwd); 
					// 判断方法是否执行成功
					if (f) {
						JOptionPane.showMessageDialog(null, "管理员登陆成功！");
						new FlyTable();
						Login.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "管理员登陆失败！");
					}
				} else if (jcbstr.equals("用户登录")) {
					User u = new User(0, uname, upwd);
					// 调用dao方法,获得影响行数
					User user = ud.login(u);
					// 判断方法是否执行成功
					if (user.getUname() != null && !user.getUname().equals("")) {
						JOptionPane.showMessageDialog(null, "用户登陆成功！");
						new YdTable(user);
						Login.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "用户登陆失败！");
					}
				}
			}
		});
		
		// 注册
		jbb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Zc();
			}
		});

		// 将小容器加入总容器中
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
