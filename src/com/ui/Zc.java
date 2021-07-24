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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.dao.UserDao;
import com.entity.User;

public class Zc extends JFrame {

	// �ű��������
	private JPanel jpn = new JPanel();
	// ���û��������룬��½��ȡ����ť������
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("�û���:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlb = new JLabel("��  ��:");
	private JPasswordField jpf = new JPasswordField(16);

	private JButton jbb = new JButton("ע��");

	// newһ��dao��
	UserDao ud = new UserDao();

	// ������
	private JPanel jpz = new JPanel();

	public Zc() {
		this.setTitle("ע�����");
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);// ���ô����޷��ı��С
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		// �û����Լ����ı����λ��
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(jla, gbc);// ��Լ��������Լ��jla(����)
		jpa.add(jla);// ��jla���ӵ�������

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jtf, gbc);
		jpa.add(jtf);

		// �����Լ����ı����λ��
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(jlb, gbc);
		jpa.add(jlb);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(jpf, gbc);
		jpa.add(jpf);

		// ��ťλ��
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// ����
		JLabel jlc = new JLabel("��Ʊ����ϵͳ�û�ע����棡");
		jlc.setFont(new Font("����", Font.BOLD, 20));
		jpn.add(jlc);

		// ע��
		jbb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ��ȡ��Ӧ�ı����ֵ
				String uname = jtf.getText();
				// ���������ֵ
				String upwd = jpf.getText();
				User u = new User(0, uname, upwd);
				// ����dao���������Ӱ������
				int n = ud.add(u);
				// �жϷ����Ƿ�ִ�гɹ�
				if (n>0) {
					JOptionPane.showMessageDialog(null, "ע��ɹ���");
					Zc.this.dispose();
					new Login();
				} else {
					JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
				}
			}
		});

		// ��С����������������
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
