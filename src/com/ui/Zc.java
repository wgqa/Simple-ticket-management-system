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

import com.dao.UserDao;
import com.entity.User;

public class Zc extends JFrame {

	// 放标题的容器
	private JPanel jpn = new JPanel();
	// 放用户名，密码，登陆，取消按钮的容器
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("用户名:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlb = new JLabel("密  码:");
	private JPasswordField jpf = new JPasswordField(16);

	private JButton jbb = new JButton("注册");

	// new一个dao类
	UserDao ud = new UserDao();

	// 总容器
	private JPanel jpz = new JPanel();

	public Zc() {
		this.setTitle("注册界面");
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);// 设置窗体无法改变大小
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

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

		// 按钮位置
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// 标题
		JLabel jlc = new JLabel("机票购买系统用户注册界面！");
		jlc.setFont(new Font("宋体", Font.BOLD, 20));
		jpn.add(jlc);

		// 注册
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 获取对应文本框的值
				String uname = jtf.getText();
				// 获得密码框的值
				String upwd = jpf.getText();
				User u = new User(0, uname, upwd);
				// 调用dao方法，获得影响行数
				int n = ud.add(u);
				// 判断方法是否执行成功
				if (n>0) {
					JOptionPane.showMessageDialog(null, "注册成功！");
					Zc.this.dispose();
					new Login();
				} else {
					JOptionPane.showMessageDialog(null, "注册失败！");
				}
			}
		});

		// 将小容器加入总容器中
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
