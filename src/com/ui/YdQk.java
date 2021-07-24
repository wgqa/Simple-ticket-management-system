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

	// ����
	public JPanel jpz = new JPanel();

	// �ײ�
	private JPanel jpfoot = new JPanel();

	// ��
	private JTable jtb = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane jsp = new JScrollPane(jtb);

	public void myShow(List<Order> cs) {
		// �����ǰ������
		// ȡ��������ǰ�ж���������
		int rows = dtm.getRowCount();
		for (int i = 0; i < rows; i++) {
			dtm.removeRow(0);// �����±��Ƴ�
		}
		for (Order u : cs) {// ��������
			// ���������ӵ���ģʽ��
			Vector vec = new Vector();
			vec.add(u.getOid());
			vec.add(u.getUida());
			vec.add(u.getJid());
			vec.add(u.getGmsj());
			dtm.addRow(vec);
		}
	}

	public YdQk(List<Order> cs) {
		this.setTitle("��Ʊ�������");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);

		// ������
		dtm.addColumn("����id");
		dtm.addColumn("�û�id");
		dtm.addColumn("��Ʊid");
		dtm.addColumn("����ʱ��");

		jtb.setModel(dtm);

		// ������
		myShow(cs);

		jpz.add(jpfoot, "South");
		jpz.add(jsp, "Center");

		this.getContentPane().add(jpz);
		this.setVisible(true);

	}

}
