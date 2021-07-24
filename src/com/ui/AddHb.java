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

	// �ű��������
	private JPanel jpn = new JPanel();
	// ���û��������룬��½��ȡ����ť������
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("��������:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlbb = new JLabel("�ȼ�:");
	private JComboBox jtfb = new JComboBox();
	private JLabel jlc = new JLabel("��˾����:");
	private JTextField jtfc = new JTextField(16);

	private JButton jba = new JButton("����");
	private JButton jbb = new JButton("���");

	// ������
	private JPanel jpz = new JPanel();

	public AddHb(HbTable t) {
		this.setTitle("���ӽ���");
		this.setSize(350, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// ���ô����޷��ı��С
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		jtfb.addItem("һ��");
		jtfb.addItem("����");
		jtfb.addItem("����");

		// �����Լ����ı����λ��
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbl.setConstraints(jla, gbc);// ��Լ��������Լ��jla(����)
		jpa.add(jla);// ��jla���ӵ�������

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jtf, gbc);
		jpa.add(jtf);

		// �ȼ��Լ����ı����λ��
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

		// ��ťλ��
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// ���ӷ���
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ��Ӧ�ı����ֵ
				String hbname = jtf.getText();
				String dj = jtfb.getSelectedItem() + "";
				String gsmc = jtfc.getText();
				Hb hb = new Hb(0, hbname, dj,gsmc);
				int i = new HbDao().add(hb);
				if (i > 0) {
					jtf.setText("");
					jtfc.setText("");
					JOptionPane.showMessageDialog(null, "���ӳɹ���");
					t.myShow("");
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
				}
			}
		});

		// ����¼�
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtf.setText("");
				jtfc.setText("");
			}
		});

		// ��С����������������
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
