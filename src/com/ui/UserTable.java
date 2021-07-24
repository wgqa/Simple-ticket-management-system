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
import com.dao.OrderDao;
import com.dao.UserDao;
import com.entity.Fly;
import com.entity.Order;
import com.entity.User;

public class UserTable extends JFrame {

	// 顶部
	public JPanel jpz = new JPanel();
	private JPanel jpatop = new JPanel();
	private JLabel jla = new JLabel("名字：");
	private JTextField jtfa = new JTextField(12);
	private JButton jbf = new JButton("查询");
	private JButton jba = new JButton("购买情况");

	// 底部
	private JPanel jpfoot = new JPanel();

	// 表
	private JTable jtb = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane jsp = new JScrollPane(jtb);

	private UserDao ud = new UserDao();

	public void myShow(String str) {
		// 清空以前的数据
		// 取到表中以前有多少行数据
		int rows = dtm.getRowCount();
		for (int i = 0; i < rows; i++) {
			dtm.removeRow(0);// 根据下标移除
		}
		List<User> myl = ud.getAll(str);
		for (User u : myl) {// 遍历集合
			// 将数据增加到表模式中
			Vector vec = new Vector();
			vec.add(u.getUida());
			vec.add(u.getUname());
			vec.add(u.getUpwd());
			dtm.addRow(vec);
		}
	}

	public UserTable() {
		this.setTitle("用户界面");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		
		// 容器加物件
		jpatop.add(jla);
		jpatop.add(jtfa);
		jpatop.add(jbf);
		jpatop.add(jba);

		// 表列名
		dtm.addColumn("编号");
		dtm.addColumn("名字");
		dtm.addColumn("密码");

		jtb.setModel(dtm);

		// 绑定数据
		myShow("");

		// 预定情况
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rt = jtb.getSelectedRow();// 选中的行号
				if (rt >= 0) {// 如果选中了
					int t = JOptionPane.showConfirmDialog(null, "确定查看吗？");
					if (t == 0) {
						String cid = dtm.getValueAt(rt, 0).toString();
						List<Order> cs = new OrderDao().selectByUname(Integer.parseInt(cid));
						new YdQk(cs);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择你要查看的用户!");
				}

			}
		});

		// 查询
		jbf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cj = jtfa.getText();
				myShow(cj);
			}
		});

		jpz.add(jpatop, "North");
		jpz.add(jpfoot, "South");
		jpz.add(jsp, "Center");

		this.getContentPane().add(jpz);
		this.setVisible(true);

	}

}
