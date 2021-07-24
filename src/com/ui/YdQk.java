package com.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;

import com.dao.FlyDao;
import com.dao.UserDao;
import com.entity.Fly;
import com.entity.Order;
import com.entity.User;

public class YdQk extends JFrame {

	// 顶部
	public JPanel jpz = new JPanel();

	// 底部
	private JPanel jpfoot = new JPanel();

	// 表
	private JTable jtb = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane jsp = new JScrollPane(jtb);

	public void myShow(List<Order> cs) {
		// 清空以前的数据
		// 取到表中以前有多少行数据
		int rows = dtm.getRowCount();
		for (int i = 0; i < rows; i++) {
			dtm.removeRow(0);// 根据下标移除
		}
		for (Order u : cs) {// 遍历集合
			// 将数据增加到表模式中
			Vector vec = new Vector();
			vec.add(u.getOid());
			vec.add(u.getUida());
			vec.add(u.getJid());
			vec.add(u.getGmsj());
			dtm.addRow(vec);
		}
	}

	public YdQk(List<Order> cs) {
		this.setTitle("机票购买界面");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);

		// 表列名
		dtm.addColumn("购买id");
		dtm.addColumn("用户id");
		dtm.addColumn("机票id");
		dtm.addColumn("购买时间");

		jtb.setModel(dtm);

		// 绑定数据
		myShow(cs);

		jpz.add(jpfoot, "South");
		jpz.add(jsp, "Center");

		this.getContentPane().add(jpz);
		this.setVisible(true);

	}

}
