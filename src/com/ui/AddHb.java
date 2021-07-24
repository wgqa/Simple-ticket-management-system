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
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.dao.HbDao;
import com.dao.UserDao;
import com.entity.Hb;
import com.entity.User;
import com.entity.User;

public class AddHb extends JFrame {

	// 放标题的容器
	private JPanel jpn = new JPanel();
	// 放用户名，密码，登陆，取消按钮的容器
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("航班名字:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlbb = new JLabel("等级:");
	private JComboBox jtfb = new JComboBox();
	private JLabel jlc = new JLabel("公司名称:");
	private JTextField jtfc = new JTextField(16);

	private JButton jba = new JButton("增加");
	private JButton jbb = new JButton("清空");

	// 总容器
	private JPanel jpz = new JPanel();

	public AddHb(HbTable t) {
		this.setTitle("增加界面");
		this.setSize(350, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// 设置窗体无法改变大小
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		jtfb.addItem("一级");
		jtfb.addItem("二级");
		jtfb.addItem("三级");

		// 名字以及其文本框的位置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbl.setConstraints(jla, gbc);// 让约束对象来约束jla(坐标)
		jpa.add(jla);// 将jla增加到容器中

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jtf, gbc);
		jpa.add(jtf);

		// 等级以及其文本框的位置
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jlbb, gbc);
		jpa.add(jlbb);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(jtfb, gbc);
		jpa.add(jtfb);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jlc, gbc);
		jpa.add(jlc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jtfc, gbc);
		jpa.add(jtfc);

		// 按钮位置
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// 增加方法
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取对应文本框的值
				String hbname = jtf.getText();
				String dj = jtfb.getSelectedItem() + "";
				String gsmc = jtfc.getText();
				Hb hb = new Hb(0, hbname, dj,gsmc);
				int i = new HbDao().add(hb);
				if (i > 0) {
					jtf.setText("");
					jtfc.setText("");
					JOptionPane.showMessageDialog(null, "增加成功！");
					t.myShow("");
				} else {
					JOptionPane.showMessageDialog(null, "增加失败！");
				}
			}
		});

		// 清空事件
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtf.setText("");
				jtfc.setText("");
			}
		});

		// 将小容器加入总容器中
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
