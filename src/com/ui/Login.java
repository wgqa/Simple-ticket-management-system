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

import com.dao.AdminDao;
import com.dao.UserDao;
import com.entity.Admin;
import com.entity.User;

public class Login extends JFrame {

	public static void main(String[] args) {
		try {
			// ���ý�����
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// �½�һ��Login��
		new Login();
	}

	// �ű��������
	private JPanel jpn = new JPanel();
	// ���û��������룬��½��ȡ����ť������
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("�û���:");
	private JTextField jtf = new JTextField(16);
	private JLabel jlb = new JLabel("��  ��:");
	private JPasswordField jpf = new JPasswordField(16);
	private JLabel jle = new JLabel("�û����:");
	// ������
	private JComboBox jcb = new JComboBox();

	private JButton jba = new JButton("��½");
	private JButton jbb = new JButton("ע��");

	// newһ��dao��
	UserDao ud = new UserDao();

	// ������
	private JPanel jpz = new JPanel();

	public Login() {
		this.setTitle("��¼����");
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);// ���ô����޷��ı��С
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);

		// �������������ֵ
		jcb.addItem("����Ա��¼");
		jcb.addItem("�û���¼");

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

		// �û������Լ����������λ��
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jle, gbc);
		jpa.add(jle);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(jcb, gbc);
		jpa.add(jcb);

		// ��ťλ��
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jbb, gbc);
		jpa.add(jbb);

		// ����
		JLabel jlc = new JLabel("��Ʊ�������ϵͳ��");
		jlc.setFont(new Font("����", Font.BOLD, 20));
		jpn.add(jlc);

		// ��½����
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ��Ӧ�ı����ֵ
				String uname = jtf.getText();
				// ���������ֵ
				String upwd = jpf.getText();
				// ����������ֵ
				String jcbstr = jcb.getSelectedItem().toString();
				if (jcbstr.equals("����Ա��¼")) {
					// ����dao���������Ӱ������
					boolean f = new AdminDao().check(uname, upwd); 
					// �жϷ����Ƿ�ִ�гɹ�
					if (f) {
						JOptionPane.showMessageDialog(null, "����Ա��½�ɹ���");
						new FlyTable();
						Login.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "����Ա��½ʧ�ܣ�");
					}
				} else if (jcbstr.equals("�û���¼")) {
					User u = new User(0, uname, upwd);
					// ����dao����,���Ӱ������
					User user = ud.login(u);
					// �жϷ����Ƿ�ִ�гɹ�
					if (user.getUname() != null && !user.getUname().equals("")) {
						JOptionPane.showMessageDialog(null, "�û���½�ɹ���");
						new YdTable(user);
						Login.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "�û���½ʧ�ܣ�");
					}
				}
			}
		});
		
		// ע��
		jbb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Zc();
			}
		});

		// ��С����������������
		jpz.add(jpn, "North");
		jpz.add(jpa, "Center");
		this.getContentPane().add(jpz);
		this.setVisible(true);
	}

}
