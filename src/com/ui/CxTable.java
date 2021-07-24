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

import com.dao.FlyDao;
import com.dao.HbDao;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.entity.Hb;
import com.entity.User;
import com.entity.User;

public class CxTable extends JFrame {

	// 放标题的容器
	private JPanel jpn = new JPanel();
	// 放用户名，密码，登陆，取消按钮的容器
	private JPanel jpa = new JPanel();

	private JButton jba = new JButton("查询售出票数金额");
	private JButton jbb = new JButton("查询航班数量");
	private JButton jbc = new JButton("查询航班剩余票数");
	private JButton jbd = new JButton("查询票价");
	private JButton jbe = new JButton("查询公司售票点数量");
	private JButton jbf = new JButton("查询公司某月售出金额");
	private JButton jbg = new JButton("查询航班售出金额");

	// 总容器
	private JPanel jpz = new JPanel();

	public CxTable() {
		this.setTitle("查询界面");
		this.setSize(350, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// 设置窗体无法改变大小
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbl.setConstraints(jba, gbc);// 让约束对象来约束jla(坐标)
		jpa.add(jba);// 将jla增加到容器中

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jbc, gbc);
		jpa.add(jbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jbd, gbc);
		jpa.add(jbd);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jbe, gbc);
		jpa.add(jbe);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbl.setConstraints(jbf, gbc);
		jpa.add(jbf);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbl.setConstraints(jbg, gbc);
		jpa.add(jbg);

		// 查询售出票数金额
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String address=JOptionPane.showInputDialog("代售点：");
				String month=JOptionPane.showInputDialog("月份：");
				float sum=new OrderDao().select(address, month);
				JOptionPane.showMessageDialog(null, "销售额度为："+sum);
			}
		});

		// 查询航班数量
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("公司名称：");
				int hbs=new HbDao().getHbs(gsmc);
				JOptionPane.showMessageDialog(null, "航班数量为："+hbs);
			}
		});
		
		// 查询航班剩余票数
		jbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("公司名称：");
				String sj=JOptionPane.showInputDialog("时间：");
				int ps=new FlyDao().getPs(gsmc, sj);
				JOptionPane.showMessageDialog(null, "剩余票数量为："+ps);
			}
		});
		
		// 查询票价
		jbd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("公司名称：");
				String sj=JOptionPane.showInputDialog("时间：");
				String wlx=JOptionPane.showInputDialog("位置类型：");
				float pj=new FlyDao().getPs(gsmc, sj, wlx);
				JOptionPane.showMessageDialog(null, "票价为："+pj);
			}
		});
		
		// 查询公司售票点数量
		jbe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gsmc=JOptionPane.showInputDialog("公司名称：");
				int spds=new FlyDao().getSpds(gsmc);
				JOptionPane.showMessageDialog(null, "售票点数量为："+spds);
			}
		});
		
		// 查询公司某月售出金额
		jbf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String hbname=JOptionPane.showInputDialog("航班名字：");
				String month=JOptionPane.showInputDialog("时间：");
				float sce=new FlyDao().getSce(hbname, month);
				JOptionPane.showMessageDialog(null, "售出总金额为："+sce);
			}
		});
		
		// 查询某航线的销售额度
		jbg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String hbname=JOptionPane.showInputDialog("航班名字：");
				float sce=new FlyDao().getSce(hbname);
				JOptionPane.showMessageDialog(null, "售出总金额为："+sce);
			}
		});

		// 将小容器加入总容器中
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
