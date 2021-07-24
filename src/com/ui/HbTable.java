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

import com.dao.HbDao;
import com.entity.Hb;

public class HbTable extends JFrame {

	// ����
	public JPanel jpz = new JPanel();
	private JPanel jpatop = new JPanel();
	private JLabel jla = new JLabel("���ƣ�");
	private JTextField jtfb = new JTextField(10);
	private JButton jba = new JButton("��ѯ");
	private JButton jbc = new JButton("����");
	private JButton jbd = new JButton("�޸�");
	private JButton jbe = new JButton("ɾ��");

	// �ײ�
	private JPanel jpfoot = new JPanel();

	// ��
	private JTable jtb = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane jsp = new JScrollPane(jtb);

	public void myShow(String str) {
		// �����ǰ������
		// ȡ��������ǰ�ж���������
		int rows = dtm.getRowCount();
		for (int i = 0; i < rows; i++) {
			dtm.removeRow(0);// �����±��Ƴ�
		}
		List<Hb> myl = new HbDao().getAll(str);
		for (Hb s : myl) {// ��������
			// ���������ӵ���ģʽ��
			Vector vec = new Vector();
			vec.add(s.getHbid());
			vec.add(s.getHbname());
			vec.add(s.getDj());
			dtm.addRow(vec);
		}
	}

	public void del() {
		int rt = jtb.getSelectedRow();// ѡ�е��к�
		if (rt >= 0) {// ���ѡ����
			int t = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����");
			if (t == 0) {
				String id = dtm.getValueAt(rt, 0).toString();
				int n = new HbDao().delete(Integer.parseInt(id));
				if (n > 0) {
					// ˢ������
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
					myShow("");
				} else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ��������!");
		}

	}

	public HbTable() {
		this.setTitle("���ݲ鿴����");
		this.setSize(650, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);

		// ���������
		jpatop.add(jla);
		jpatop.add(jtfb);
		jpatop.add(jba);
		jpatop.add(jbc);
		jpatop.add(jbd);
		jpatop.add(jbe);

		// ������
		dtm.addColumn("����id");
		dtm.addColumn("����");
		dtm.addColumn("�ȼ�");

		jtb.setModel(dtm);

		// ������
		myShow("");

		// �����¼�
		jbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddHb(HbTable.this);
			}
		});

		// ��ѯ�¼�
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sa = jtfb.getText();
				myShow(sa);
			}
		});

		// ɾ���¼�
		jbe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				del();
			}
		});

		// �޸��¼�
		jbd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rt = jtb.getSelectedRow();// ѡ�е��к�
				if (rt >= 0) {// ���ѡ����
					int t = JOptionPane.showConfirmDialog(null, "ȷ���޸���");
					if (t == 0) {
						String id = dtm.getValueAt(rt, 0).toString();
						Hb hb = new HbDao().getOne(Integer.parseInt(id));
						new UpdateHb(HbTable.this, hb);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵļ�¼!");
				}
			}
		});

		jpz.add(jpatop, "North");
		jpz.add(jpfoot, "South");
		jpz.add(jsp, "Center");

		this.getContentPane().add(jpz);
		this.setVisible(true);

	}

}
