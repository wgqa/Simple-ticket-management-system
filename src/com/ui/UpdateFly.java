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
import java.util.List;

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

import com.dao.FlyDao;
import com.dao.HbDao;
import com.entity.Fly;
import com.entity.Hb;

public class UpdateFly extends JFrame {

	// �ű��������
	private JPanel jpn = new JPanel();
	private JPanel jpa = new JPanel();

	private JLabel jla = new JLabel("�۸�");
	private JTextField jcba = new JTextField(12);
	private JLabel jlb = new JLabel("�ɻ��ͺţ�");
	private JTextField jcbb = new JTextField(12);
	private JLabel jlc = new JLabel("��λ���ͣ�");
	private JTextField jtfc = new JTextField(12);
	private JLabel jld = new JLabel("���ࣺ");
	private JComboBox jtfd = new JComboBox();
	private JLabel jle = new JLabel("����λ�ã�");
	private JTextField jtfe = new JTextField(12);
	
	public void jiazai() {
		List<Hb> mys = new HbDao().getAll("");
		for (int i = 0; i < mys.size(); i++) {
			jtfd.addItem(mys.get(i).getHbid() + "-" + mys.get(i).getHbname());
		}
	}

	private JButton jba = new JButton("�޸�");

	// ������
	private JPanel jpz = new JPanel();

	public UpdateFly(FlyTable t, Fly fly) {
		this.setTitle("�޸Ľ���");
		this.setSize(350, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(2);
		this.setResizable(false);// ���ô����޷��ı��С
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		jpa.setLayout(gbl);
		
		// �۸��Լ����ı����λ��
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(jla, gbc);// ��Լ��������Լ��jla(����)
		jpa.add(jla);// ��jla���ӵ�������

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(jcba, gbc);
		jpa.add(jcba);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbl.setConstraints(jlb, gbc);
		jpa.add(jlb);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(jcbb, gbc);
		jpa.add(jcbb);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(jlc, gbc);
		jpa.add(jlc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbl.setConstraints(jtfc, gbc);
		jpa.add(jtfc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(jld, gbc);
		jpa.add(jld);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbl.setConstraints(jtfd, gbc);
		jpa.add(jtfd);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(jle, gbc);
		jpa.add(jle);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbl.setConstraints(jtfe, gbc);
		jpa.add(jtfe);

		// ��ťλ��
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbl.setConstraints(jba, gbc);
		jpa.add(jba);
		
		jiazai();
		
		jcba.setText(fly.getJjg()+"");
		jcbb.setText(fly.getJx());
		jtfc.setText(fly.getWlx());
		jtfe.setText(fly.getJtwz());

		// �޸ķ���
		jba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ��Ӧ�ı����ֵ
				String a = jcba.getText();
				String b = jcbb.getText();
				String c = jtfc.getText();
				String d = jtfd.getSelectedItem()+"";
				String[] ds = d.split("-");
				String fa = jtfe.getText();
				Fly f=new Fly(fly.getJid(), Float.parseFloat(a), b, c, Integer.parseInt(ds[0]),"","",fa);
				int i = new FlyDao().update(f);
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					t.myShow("");
				} else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
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
