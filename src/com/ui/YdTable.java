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

	// ����
	public JPanel jpz = new JPanel();
	private JPanel jpatop = new JPanel();
	private JLabel jla = new JLabel("��λ���ͣ�");
	private JTextField jtfa = new JTextField(12);
	private JButton jbf = new JButton("��ѯ");
	private JButton jbc = new JButton("����");
	private JButton jbd = new JButton("�������");

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
		List<Fly> myl = new FlyDao().getAll("wlx", str);
		for (Fly u : myl) {// ��������
			// ���������ӵ���ģʽ��
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
		this.setTitle("�û������Ʊ����");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		
		// ���������
		jpatop.add(jla);
		jpatop.add(jtfa);
		jpatop.add(jbf);
		jpatop.add(jbc);
		jpatop.add(jbd);

		// ������
		dtm.addColumn("��Ʊid");
		dtm.addColumn("�۸�");
		dtm.addColumn("�ɻ��ͺ�");
		dtm.addColumn("��λ����");
		dtm.addColumn("����id");
		dtm.addColumn("ʱ��");
		dtm.addColumn("��Ʊ��");
		dtm.addColumn("����λ��");

		jtb.setModel(dtm);

		// ������
		myShow("");

		// ��ѯ
		jbf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cj = jtfa.getText();
				myShow(cj);
			}
		});

		// Ԥ��
		jbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					int rt = jtb.getSelectedRow();// ѡ�е��к�
					if (rt >= 0) {// ���ѡ����
						int t = JOptionPane.showConfirmDialog(null, "ȷ��������");
						if (t == 0) {
							String cid = dtm.getValueAt(rt, 0).toString();
							Order o = new Order(0, u.getUida(), Integer.parseInt(cid), new Date().toLocaleString());
							int na = new OrderDao().add(o);
							if (na > 0) {
								// ˢ������
								JOptionPane.showMessageDialog(null, "����ɹ���");
								myShow("");
							} else {
								JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫ����Ļ�Ʊ!");
					}
			}
		});

		// Ԥ�����
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
