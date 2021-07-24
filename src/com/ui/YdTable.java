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

public class YdTable extends JFrame {

	// 顶部
	public JPanel jpz = new JPanel();
	private JPanel jpatop = new JPanel();
	private JLabel jla = new JLabel("座位类型：");
	private JTextField jtfa = new JTextField(12);
	private JButton jbf = new JButton("查询");
	private JButton jbc = new JButton("购买");
	private JButton jbd = new JButton("购买情况");

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
		List<Fly> myl = new FlyDao().getAll("wlx", str);
		for (Fly u : myl) {// 遍历集合
			// 将数据增加到表模式中
			Vector vec = new Vector();
			vec.add(u.getJid());
			vec.add(u.getJjg());
			vec.add(u.getJx());
			vec.add(u.getWlx());
			vec.add(u.getHbid());
			vec.add(u.getSj());
			vec.add(u.getSpd());
			vec.add(u.getJtwz());
			dtm.addRow(vec);
		}
	}

	public YdTable(User u) {
		this.setTitle("用户购买机票界面");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		
		// 容器加物件
		jpatop.add(jla);
		jpatop.add(jtfa);
		jpatop.add(jbf);
		jpatop.add(jbc);
		jpatop.add(jbd);

		// 表列名
		dtm.addColumn("机票id");
		dtm.addColumn("价格");
		dtm.addColumn("飞机型号");
		dtm.addColumn("座位类型");
		dtm.addColumn("航班id");
		dtm.addColumn("时间");
		dtm.addColumn("售票点");
		dtm.addColumn("具体位置");

		jtb.setModel(dtm);

		// 绑定数据
		myShow("");

		// 查询
		jbf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cj = jtfa.getText();
				myShow(cj);
			}
		});

		// 预定
		jbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					int rt = jtb.getSelectedRow();// 选中的行号
					if (rt >= 0) {// 如果选中了
						int t = JOptionPane.showConfirmDialog(null, "确定购买吗？");
						if (t == 0) {
							String cid = dtm.getValueAt(rt, 0).toString();
							Order o = new Order(0, u.getUida(), Integer.parseInt(cid), new Date().toLocaleString());
							int na = new OrderDao().add(o);
							if (na > 0) {
								// 刷新数据
								JOptionPane.showMessageDialog(null, "购买成功！");
								myShow("");
							} else {
								JOptionPane.showMessageDialog(null, "购买失败！");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "请选择你要购买的机票!");
					}
			}
		});

		// 预定情况
		jbd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					List<Order> cs =new OrderDao().selectByUname(u.getUida());
					new YdQk(cs);
					YdTable.this.dispose();
			}
		});

		jpz.add(jpatop, "North");
		jpz.add(jpfoot, "South");
		jpz.add(jsp, "Center");

		this.getContentPane().add(jpz);
		this.setVisible(true);

	}

}
