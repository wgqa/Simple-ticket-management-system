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

	// ����
	public JPanel jpz = new JPanel();
	private JPanel jpatop = new JPanel();
	private JLabel jla = new JLabel("���֣�");
	private JTextField jtfa = new JTextField(12);
	private JButton jbf = new JButton("��ѯ");
	private JButton jba = new JButton("�������");

	// �ײ�
	private JPanel jpfoot = new JPanel();

	// ��
	private JTable jtb = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane jsp = new JScrollPane(jtb);

	private UserDao ud = new UserDao();

	public void myShow(String str) {
		// �����ǰ������
		// ȡ��������ǰ�ж���������
		int rows = dtm.getRowCount();
		for (int i = 0; i < rows; i++) {
			dtm.removeRow(0);// �����±��Ƴ�
		}
		List<User> myl = ud.getAll(str);
		for (User u : myl) {// ��������
			// ���������ӵ���ģʽ��
			Vector vec = new Vector();
			vec.add(u.getUida());
			vec.add(u.getUname());
			vec.add(u.getUpwd());
			dtm.addRow(vec);
		}
	}

	public UserTable() {
		this.setTitle("�û�����");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		
		// ���������
		jpatop.add(jla);
		jpatop.add(jtfa);
		jpatop.add(jbf);
		jpatop.add(jba);

		// ������
		dtm.addColumn("���");
		dtm.addColumn("����");
		dtm.addColumn("����");

		jtb.setModel(dtm);

		// ������
		myShow("");

		// Ԥ�����
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rt = jtb.getSelectedRow();// ѡ�е��к�
				if (rt >= 0) {// ���ѡ����
					int t = JOptionPane.showConfirmDialog(null, "ȷ���鿴��");
					if (t == 0) {
						String cid = dtm.getValueAt(rt, 0).toString();
						List<Order> cs = new OrderDao().selectByUname(Integer.parseInt(cid));
						new YdQk(cs);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�鿴���û�!");
				}

			}
		});

		// ��ѯ
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
